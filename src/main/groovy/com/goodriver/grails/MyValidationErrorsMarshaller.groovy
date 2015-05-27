package com.goodriver.grails

import grails.converters.JSON;
import org.grails.web.json.JSONWriter;
import org.grails.web.converters.exceptions.ConverterException;
import org.grails.web.converters.marshaller.ObjectMarshaller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Created by ycheng on 5/26/2015.
 */
public class MyValidationErrorsMarshaller implements ObjectMarshaller<JSON>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public boolean supports(Object object) {
        return object instanceof Errors;
    }

    public void marshalObject(Object object, JSON json) throws ConverterException {
        Errors errors = (Errors) object;
        JSONWriter writer = json.getWriter();

        try {
            writer.object();
            writer.key("errors");
            writer.array();

            for (Object o : errors.getAllErrors()) {
                if (o instanceof FieldError) {
                    FieldError fe = (FieldError) o;
                    writer.object();
                    json.property("object", fe.getObjectName());
                    json.property("field", fe.getField());
                    json.property("rejected-value", fe.getRejectedValue());
                    Locale locale = LocaleContextHolder.getLocale();
                    if (applicationContext != null) {
                        json.property("message", applicationContext.getMessage(fe, locale));
                    }
                    else {
                        json.property("message", fe.getDefaultMessage());
                    }
                    writer.endObject();
                } else if (o instanceof ObjectError) {
                    ObjectError fe = (ObjectError) o;
                    writer.object();
                    json.property("object", fe.getObjectName());
                    Locale locale = LocaleContextHolder.getLocale();
                    if (applicationContext != null) {
                        json.property("message", applicationContext.getMessage(fe, locale));
                    }
                    else {
                        json.property("message", fe.getDefaultMessage());
                    }
                    writer.endObject();
                }
            }
            writer.endArray();
            writer.endObject();
        }
        catch (ConverterException ce) {
            throw ce;
        }
        catch (Exception e) {
            throw new ConverterException("Error converting Bean with class " + object.getClass().getName(), e);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}

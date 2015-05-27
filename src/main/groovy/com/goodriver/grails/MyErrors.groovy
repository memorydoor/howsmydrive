package com.goodriver.grails

import org.springframework.validation.AbstractErrors
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

/**
 * Created by ycheng on 5/27/2015.
 */
class MyErrors extends AbstractErrors {

    List<ObjectError> objectErrors

    List<FieldError> fieldErrors

    String getObjectName() {
        return null
    }

    void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

    }

    void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

    }

    void addAllErrors(Errors errors) {
        objectErrors.addAll(errors.allErrors)
        fieldErrors.addAll(errors.fieldErrors)
    }

    List<ObjectError> getGlobalErrors() {
        return objectErrors
    }

    List<FieldError> getFieldErrors() {
        return fieldErrors
    }

    Object getFieldValue(String field) {
        return null
    }
}

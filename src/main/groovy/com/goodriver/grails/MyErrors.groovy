package com.goodriver.grails

import org.springframework.validation.AbstractErrors
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

/**
 * Created by ycheng on 5/27/2015.
 */
class MyErrors extends AbstractErrors {

    List<ObjectError> objectErrors = new ArrayList<ObjectError>()

    List<FieldError> fieldErrors = new ArrayList<FieldError>()

    String getObjectName() {
        return null
    }

    void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

    }

    void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

    }

    void addAllErrors(Errors errors) {
        objectErrors.addAll(errors.globalErrors)
        fieldErrors.addAll(errors.fieldErrors)
    }

    void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError)
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

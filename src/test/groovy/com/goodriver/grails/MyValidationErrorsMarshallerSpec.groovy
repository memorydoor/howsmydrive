package com.goodriver.grails

import grails.converters.JSON
import org.grails.web.json.JSONWriter
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError
import spock.lang.Specification

/**
 * Created by ycheng on 5/26/2015.
 */
class MyValidationErrorsMarshallerSpec extends Specification {

    void "Test marshalObject handles org.springframework.validation.ObjectError"() {
        given:
        ObjectError objectError = new ObjectError('test', 'Error happening on test object.')

        List<ObjectError> allErrors = [objectError]

        MyValidationErrorsMarshaller marshaller = new MyValidationErrorsMarshaller()
        Errors errors = Mock(Errors) {
            1 * getAllErrors() >> allErrors
        }

        JSON json = new JSON()

        StringWriter stringWriter = new StringWriter()
        json.writer = new JSONWriter(stringWriter)

        when:
        marshaller.marshalObject(errors, json)

        then:
        assert stringWriter.toString() == '{"errors":[{"object":"test","message":"Error happening on test object."}]}'
    }
}

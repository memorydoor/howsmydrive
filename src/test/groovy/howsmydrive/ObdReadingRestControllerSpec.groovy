package howsmydrive

import com.jayway.jsonpath.JsonPath
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * Created by yongcheng on 6/1/15.
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(ObdReadingRestController)
@Mock([ObdReading])
class ObdReadingRestControllerSpec extends Specification {
    void "test postObdReading can handle empty json array"() {
        when:
        request.json = "[]"
        controller.postObdReading()

        then:
        response.status == 200
        response.json.message == 'Saved 0 obdReadings.'
        ObdReading.all.size() == 0
    }

    void "test postObdReading can give nullable validation for all nessesary field"() {
        when:
        request.json = "[{}]"
        controller.postObdReading()

        then:
        response.status == 422
        response.json.errors.size() == 6
        ObdReading.all.size() == 0
    }

    void "test postObdReading doesn't save when tripId is not a number"() {
        when:
        request.json = '[{"tripId":"abc"}]'
        controller.postObdReading()

        then:
        def path = '$.errors[?(@.rejected-value==abc)]'
        Object jsonPathResult = JsonPath.read(response.json.toString(),path);

        assert response.status == 422
        assert jsonPathResult.size() == 1
        ObdReading.all.size() == 0
    }
}

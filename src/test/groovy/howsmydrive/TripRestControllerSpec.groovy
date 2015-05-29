package howsmydrive

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(TripRestController)
@Mock([Trip])
class TripRestControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test postTrip can handle empty json array"() {
        when:
        request.json = "[{}]"
        controller.postTrips()

        then:
        response.json.error == 'no data'
    }

    void "test postTrip can handle one trip"() {
        when:
        request.json = '[{"tripId":"1","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'
        controller.postTrips()

        then:
        response.status == 200
        response.json.message == 'Saved 1 trip.'
    }

    void "test postTrip can handle two trip"() {
        when:
        request.json = '''[{"tripId":"1","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   },
                {"tripId":"2","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'''
        controller.postTrips()

        then:
        response.status == 200
        response.json.message == 'Saved 2 trip.'
    }

    void "test postTrip can check the startDate format"() {
        when:
        request.json = '[{"tripId":"1","endDate":"2015/05/27 00:00:00 000", "engineRpmMax":12,"speed":50,"startDate":"2015/05/27 00:00:00"}]'
        controller.postTrips()

        then:
        response.json.errors.object[0] == 'howsmydrive.Trip'

    }
}
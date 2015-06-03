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
        Trip.all.size() == 0
    }

    void "test postTrip can handle one trip"() {
        when:
        request.json = '[{"tripId":"1","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'
        controller.postTrips()

        then:
        response.status == 200
        response.json.message == 'Saved 1 trip.'
        Trip.all.size() == 1
    }

    void "test postTrip can handle two trip"() {
        when:
        request.json = '''[{"tripId":"1","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   },
                {"tripId":"2","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'''
        controller.postTrips()

        then:
        response.status == 200
        response.json.message == 'Saved 2 trip.'
        Trip.all.size() == 2
    }

    void "test postTrip can check the startDate format"() {
        when:
        request.json = '[{"tripId":"1","endDate":"2015/05/27 00:00:00 000", "engineRpmMax":12,"speed":50,"startDate":"2015/05/27 00:00:00"}]'
        controller.postTrips()

        then:
        response.json.errors.object[0] == 'howsmydrive.Trip'
        Trip.all.size() == 0

    }

    void "test postTrip doesn't save any trip when any validation error"() {
        when:
        request.json = '''[{"tripId":"1","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   },
                {"tripId":"2a","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'''
        controller.postTrips()

        then:
        response.json.errors.object[0] == 'howsmydrive.Trip'
        Trip.all.size() == 0
    }

    void "test postTrip to display all invalid fields and only return one message per field for same object"() {
        when:
        request.json = '''[{"tripId":"1a","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   },
                {"tripId":"2a","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'''
        controller.postTrips()

        then:
        response.json.errors.object[1] == 'howsmydrive.Trip'
        response.json.errors.field[1] == 'tripId'
        response.json.errors["rejected-value"][1] == '2a'
        response.json.errors.message[1] == 'Unable to parse number [2a]'
        response.json.errors.size() == 2
        Trip.all.size() == 0
    }

    void "test postTrip display nullable message when tripId is missing"() {
        when:
        request.json = '[{"endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'
        controller.postTrips()

        then:
        response.status == 422
        response.json.errors.object[0] == 'howsmydrive.Trip'
        response.json.errors.field[0] == 'tripId'
        response.json.errors["rejected-value"][0].toString() == "null"
        response.json.errors.message[0] == 'Property [tripId] of class [class howsmydrive.Trip] cannot be null'
        response.json.errors.size() == 1
        Trip.all.size() == 0
    }

    void "test postTrips response all errors besides for tripId"() {
        when:
        request.json = '[{"tripId":"1"}]'
        controller.postTrips()

        then:
        response.status == 422
        response.json.errors.size() == 4
    }

}
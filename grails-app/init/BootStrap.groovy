import grails.converters.JSON
import grails.util.Environment
import howsmydrive.Trip

class BootStrap {

    def init = { servletContext ->
        if(Environment.current == Environment.DEVELOPMENT) {

            def json = '''[{"tripId":"1","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   },
                {"tripId":"2","endDate":"2015-05-27 00:00:00.678", "engineRpmMax":12,"speed":50,"startDate":"2015-05-27 00:00:00.678"   }]'''

            def triplist = JSON.parse(json)
            triplist.each {
                def trip = new Trip(JSON.parse(it.toString()))
                println trip.save(flush:true)
            }
        }
    }
    def destroy = {
    }
}

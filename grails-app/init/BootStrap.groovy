import grails.converters.JSON
import grails.util.Environment
import howsmydrive.ObdReading
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

            //add ObdReading data
            def obdJson = '''[{"tripId":"1", "latitude":"11111", "longitude":"11111", "timestamp":"2015-05-27 00:00:00.000", "readings":"s", "speed":"25"},
                              {"tripId":"1", "latitude":"11111", "longitude":"11111", "timestamp":"2015-05-27 00:00:00.500", "readings":"s", "speed":"26"},
                              {"tripId":"1", "latitude":"11111", "longitude":"11111", "timestamp":"2015-05-27 00:00:01.000", "readings":"s", "speed":"24"},
                              {"tripId":"1", "latitude":"11111", "longitude":"11111", "timestamp":"2015-05-27 00:00:01.500", "readings":"s", "speed":"29"}]'''

            def obdlist = JSON.parse(obdJson)
            obdlist.each {
                def obdReading = new ObdReading(JSON.parse(it.toString()))
                println obdReading.save(flush:true)
            }
        }
    }
    def destroy = {
    }
}

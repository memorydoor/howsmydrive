package howsmydrive

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional
class ObdReadingRestController extends RestfulController{

    static responseFormats = ['json']

    ObdReadingRestController() {
        super(ObdReading)
    }

    def index() {

        //println obdReading
        respond(null, [status: 200])
    }

    def postObdReading() {
        def o =  getObjectToBind()

        o.JSON.obdReadings.each {
            println it.toString()
        }

        respond(null, [status: 200])
    }
}

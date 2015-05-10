package howsmydrive

import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional
import grails.artefact.controller.RestResponder

@Transactional
class TripRestController extends RestfulController{

    static responseFormats = ['json']

    TripRestController() {
        super(Trip)
    }

    def index(Trip trip) {

        println trip
        respond(null, [status: 200])
    }

    def postTrips() {
        def o =  getObjectToBind()

        o.JSON.trips.each {
            def trip = new Trip(JSON.parse(it.toString()))
            println System.currentTimeMillis()
            println trip
            trip.save(flush:true)
            println trip
        }

        respond(null, [status: 200])
    }

}

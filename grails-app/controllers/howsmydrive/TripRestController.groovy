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
        respond([status: 200])
    }

    def postTrips() {
        def o =  getObjectToBind()

        o.JSON.trips.each {
            println it.toString()
            def trip = new Trip(JSON.parse(it.toString()))

            trip.tripId = it.id
            println System.currentTimeMillis()
            println trip
            trip.save(flush:true)
            println trip
        }

        respond([status: 200])
    }

}

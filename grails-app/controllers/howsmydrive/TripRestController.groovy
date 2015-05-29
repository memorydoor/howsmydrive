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

        def noError = true
        def numberOfTrips = 0

        println o.JSON
        if (o.JSON.size() == 1 && o.JSON[0].size() == 0) {
            respond ([error: "no data"])
        }

        def tempTrip
        o.JSON.each {
            def trip = new Trip(JSON.parse(it.toString()))

            if (trip.validate()) {
                trip.save(flush:true)
                numberOfTrips++;
            } else {
                tempTrip = trip
                trip.errors.each {
                    println it
                }
                noError = false
            }
        }

        if (noError) {
            respond([message: "Saved $numberOfTrips trip."])
        }

        respond tempTrip
    }

}

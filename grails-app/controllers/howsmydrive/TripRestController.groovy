package howsmydrive

import com.goodriver.grails.MyErrors
import grails.converters.JSON
import grails.rest.RestfulController
import grails.transaction.Transactional
import grails.artefact.controller.RestResponder
import org.springframework.validation.FieldError

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
        def tripList = []
        def myErrors
        o.JSON.each {
            def trip = new Trip(JSON.parse(it.toString()))
            tripList.add(trip)
            if (!trip.validate()) {
                noError = false
                tempTrip = trip
                trip.errors.each {
                    println it
                }

                if (!myErrors)
                    myErrors = new MyErrors()


                def checkedFields = new HashSet<String>()
                trip.errors.fieldErrors.each {
                    def fieldName = it.field
                    if (!checkedFields.contains(fieldName)) {
                        List<FieldError> fieldErrors = trip.errors.getFieldErrors(fieldName)

                        if (fieldErrors.size() > 1) {
                            fieldErrors.each {
                                if(it.rejectedValue)
                                    myErrors.addFieldError(it)
                            }
                        } else {
                            myErrors.addFieldError(fieldErrors.get(0))
                        }
                    }
                    checkedFields.add(fieldName)
                }
            };
        }

        if(noError) {
            tripList.each {
                it.save(flush:true)
            }
        }

        if (noError) {
            respond([message: "Saved $tripList.size trip."])
        }

        respond myErrors
    }


}

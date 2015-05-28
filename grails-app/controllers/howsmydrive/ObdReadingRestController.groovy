package howsmydrive

import com.goodriver.grails.MyErrors
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
        respond([status: 200])
    }

    def postObdReading() {
        def o =  getObjectToBind()
        def myErrors

        o.JSON.obdReadings.each {
            println it.toString()
            def obdReading = new ObdReading(JSON.parse(it.toString()))
            println System.currentTimeMillis()
            println obdReading

            obdReading.readings = it.readings.toString()

            if (obdReading.validate()) {
                obdReading.save(flush: true)
            } else {
                if (!myErrors){
                    myErrors = new MyErrors()
                }
                myErrors.addAllErrors(obdReading.errors)
            }
        }

        if (myErrors) {
            respond myErrors
        } else {
            respond([status: 200])
        }
    }
}

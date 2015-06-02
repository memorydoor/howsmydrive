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

        o.JSON.each {
            println it.toString()
            def obdReading = new ObdReading(JSON.parse(it.toString()))

            if (obdReading.validate()) {

            } else {
                respond obdReading
            }


        }

        if (myErrors) {
            respond myErrors
        } else {
            respond([message: "Saved $o.JSON.obdReading.size obdReadings."])
        }
    }
}

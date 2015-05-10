package howsmydrive

//import grails.artefact.controller.RestResponder
import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional
class RichObdReadingController extends RestfulController {

    static responseFormats = ['json']

    RichObdReadingController() {
        super(RichObdReading)
    }

    def index() {
        def json =  [message: "hello"]
        respond json
    }
}

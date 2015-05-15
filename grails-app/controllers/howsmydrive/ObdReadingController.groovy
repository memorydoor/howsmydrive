package howsmydrive

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ObdReadingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ObdReading.list(params), model:[obdReadingCount: ObdReading.count()]
    }

    def show(ObdReading obdReading) {
        respond obdReading
    }

    def create() {
        respond new ObdReading(params)
    }

    @Transactional
    def save(ObdReading obdReading) {
        if (obdReading == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (obdReading.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond obdReading.errors, view:'create'
            return
        }

        obdReading.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'obdReading.label', default: 'ObdReading'), obdReading.id])
                redirect obdReading
            }
            '*' { respond obdReading, [status: CREATED] }
        }
    }

    def edit(ObdReading obdReading) {
        respond obdReading
    }

    @Transactional
    def update(ObdReading obdReading) {
        if (obdReading == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (obdReading.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond obdReading.errors, view:'edit'
            return
        }

        obdReading.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'obdReading.label', default: 'ObdReading'), obdReading.id])
                redirect obdReading
            }
            '*'{ respond obdReading, [status: OK] }
        }
    }

    @Transactional
    def delete(ObdReading obdReading) {

        if (obdReading == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        obdReading.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'obdReading.label', default: 'ObdReading'), obdReading.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'obdReading.label', default: 'ObdReading'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

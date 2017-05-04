package com.ttn.linksharing

class UtilController {

    def index() {
        log.info("Success")
        log.warn("warning..")
        log.error("error....")

        render "Params :  ${params}"
        log.info("Params ${params}")
    }
}

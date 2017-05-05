package com.ttn.linksharing

import grails.interceptors.Matcher


class ApplicationInterceptor {

    ApplicationInterceptor(){
        matchAll()
    }
    boolean before() { true }

    boolean after() {
        log.info "After executing the ${params}   action - $actionName   "
       true
    }

    void afterView() {
        // no-op
    }
}

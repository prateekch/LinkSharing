package com.ttn.linksharing


class LoginCheckInterceptor {

    LoginCheckInterceptor(){
       // matchAll().excludes(controller:'login')
    }
    boolean before() {
        if(!session.user){
            redirect(controller: 'login')
            return  false
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}

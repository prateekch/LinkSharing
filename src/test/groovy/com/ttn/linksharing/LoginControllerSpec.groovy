package com.ttn.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
    /*void "loginHandlerTestCase"(){
       given:
        User user = User.findByUsernameAndPassword("prateek12", "12345")
        when:
          controller.loginHandler()
        then:
        session.user==user

    }*/

}

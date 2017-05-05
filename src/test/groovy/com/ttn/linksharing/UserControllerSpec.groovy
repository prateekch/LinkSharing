package com.ttn.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    void "testforIndex"(){
        given:
        User user =new User(firstName: "prateek", lastName: "chaudhary", email: "prateek@gmail.com",
                password: "12345", username: "prateek12")

        session.user=user
        when:
        controller.index()
        then:
        response.text=="prateek12"
    }
}

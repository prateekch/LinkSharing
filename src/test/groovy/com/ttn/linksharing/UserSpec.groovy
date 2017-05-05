package com.ttn.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "UserValidations"() {
        setup:
        User user = new User(email: email, password: password,firstname: firstname, lastname: lastname,username: username,
                photo: photo, admin: admin, active: active)
        when:
        Boolean result = user.validate()
        then:
        result == valid
        where:
         firstname | lastname    |username      | email                           | password | photo | admin | active |valid
         "prateek" | "chaudhary" | "prateek12"  |"prateekchaudhary123@gmail.com" | "123456"    | null  | true  | true   |true
         "sumit"   | "sharma"    | "sumit12"    |"sumitsharma@gmail.com"         | " "      | null  | false  | true   |false
        " abcd"    |"efg"        |"prateek12"   |"xyz"                           | "1234567 "|null | false |   true |  false
    }

    void "test for toString of User"() {
        given:
        User user = new User(firstName: "prateek", lastName: "chaudhary", email: "prateek@gmail.com",
                password: "12345", username: "prateek12" +
                "")
        when:
        user.toString()

        then:
        "prateek12"
    }
   /* void "testUserPasswordConstraints"() {

        User user = new User(firstName: "prateek", lastName: "chaudhary", email: "prateek@gmail.com",
                password: "12345",confirmpassword: "12345" ,username: "prateek12")

       user.password="abcde"
        user.confirmpassword="xyz"
        user.validate()
        assertNotNull(user.errors["confirmPassword"])
// Validation message for password and confirmPassword field do not match

    }*/


    }


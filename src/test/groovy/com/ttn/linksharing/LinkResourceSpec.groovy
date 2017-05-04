package com.ttn.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    @Unroll("#sno")
    void "validateLinkResource"(){
        setup:
        User user = new User(email:"prateek.chaudhary@tothenew.com", userName: "prateek",
                password: "1234", firstName: "Prateek",
                lastName: "Chaudhary")

        Topic topic = new Topic(name: "Grails", visibility: Visibility.PRIVATE, createdBy: user)
        LinkResource linkResource=new LinkResource(url:url, description:description,createdBy: user,topic:topic)
        when:
        Boolean result=linkResource.validate()
        then:
        result==valid
        where:
        sno | url                       |description   |valid
        1   |"http://www.tothenew.com/" |""  |     false
        2   | null                      |"to the new"   |false



    }
    void "test for toString in LinkResource"() {
        given:
        LinkResource linkResource = new LinkResource(description: "description",
                topic: new Topic(), createdBy: new User(), url: "https://www.google.co.in")

        when:
        linkResource.toString()

        then:
        "https://www.google.co.in"
    }
}

package com.ttn.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
    def "validateDocumentResource"() {

        setup:
        DocumentResource documentResource = new DocumentResource(filePath: filePath, description: description)

        when:
        Boolean result = documentResource.validate()

        then:
        result == valid

        where:
        description   | filePath      | valid
        " "           | '/home/prateek'  | false
        null          | '/home/prateek'  | false
        "description" | ' '             | false
        "description" | null            | false
    }
    void "Document resource test"() {
        given:
        DocumentResource documentResource = new DocumentResource(description: "description",
                topic: new Topic(), createdBy: new User(), filePath: "/home/prateek/Desktop/LinkSharing/grails-app/assets/default")

        when:
        documentResource.toString()

        then:
        "/home/prateek/Desktop/LinkSharing/grails-app/assets/default"
    }

}

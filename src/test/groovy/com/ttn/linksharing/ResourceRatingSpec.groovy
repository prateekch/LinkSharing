package com.ttn.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("#sno")
void "validateResourceRating"(){
        given:
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: score)

        when:
        Boolean valid = resourceRating.validate()

        then:
        valid == result

        where:
       sno| resource               | user       | score | result
       1| new DocumentResource() | new User() | 10    | false
       2| null                   | new User() | 3     | false
       3| new DocumentResource() | null       | 3     | false

    }
}

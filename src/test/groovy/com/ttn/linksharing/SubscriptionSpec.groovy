package com.ttn.linksharing

import enums.Seriousness
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("#sno")
   void "validateSubscription"(){
        given:
        Subscription subscription=new Subscription(user:user,topic:topic ,
                seriousness:seriousness)
        when:
        Boolean result= subscription.validate()

        then:
        result==valid

        where:
        sno | user | topic | seriousness              |valid
        1| new User() | new Topic()|Seriousness.SERIOUS| true
        2| null |null    | Seriousness.CASUAL |  false

   }
}

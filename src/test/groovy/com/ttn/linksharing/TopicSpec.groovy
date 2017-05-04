package com.ttn.linksharing

import enums.Visibility
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("#sno")
    void "ValidationgTopic"(){
        given:
        Topic topic=new Topic(topicName:topicname, visibility:visiblity, createdBy:createdBy)

       when:
       Boolean result=topic.validate()
       then:
       result==valid
       where:
       sno|topicname | visiblity        |createdBy |valid
        1|"grails" |null                 | new User()  |false
        2|null   |Visibility.PUBLIC     | new User()   |false
        3|"" |Visibility.PRIVATE        | new User()   |false


    }
    void "test for topic"(){
        given:
        Topic topic = new Topic(topicName:"topic1" ,visibility:Visibility.PUBLIC,createdBy:new User())
        when:
        topic.toString()

        then:
        "topic1"
    }
    }

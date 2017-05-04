package com.ttn.linksharing

import enums.Seriousness
import enums.Visibility

class Topic {

    String topicName
    Visibility visibility
    User createdBy
    Date dateCreated
    Date lastUpdated

    static belongsTo = [createdBy:User]
    static constraints = {
        topicName(nullable: false,blank: false,unique: ['createdBy'])
        createdBy(nullable: false)
        visibility(nullable: false)
    }
    def afterInsert() {
        Subscription subscription = new Subscription()
        subscription.user = createdBy
        subscription.seriousness = Seriousness.VERY_SERIOUS
        Topic topic=Topic.findByTopicNameAndCreatedBy(topicName,createdBy)
        subscription.topic=topic

        subscription.withNewSession {
            if (subscription.save(flush: true)) {
                log.info "Subscribed user"
            } else
                println subscription.errors.allErrors
        }
    }
    static hasMany = [subscriptions:Subscription, resources:Resource]
    String toString(){
        return "${topicName}"
    }
}

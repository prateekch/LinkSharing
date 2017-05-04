package com.ttn.linksharing

import enums.Seriousness



class Subscription {
    User user
    Topic topic
    Seriousness seriousness
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User]

    static constraints = {
        user(nullable: false)
        topic(nullable: false)
        seriousness(nullable: false)
        topic(unique: ['user'])
    }
    String toString(){
        return "${user} subscribed to ${topic}"
    }
}

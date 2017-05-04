package com.ttn.linksharing

class ReadingItem {
    User user
    boolean isRead
    Resource resource

    static belongsTo = [user:User,resource:Resource]
    static constraints = {
        user(nullable: false)
        isRead(nullable:false)
        resource(nullable: false,unique: ['user'])

    }
}

package com.ttn.linksharing
abstract class Resource {

    User createdBy
    String description
    Topic topic

    Date dateCreated
    Date lastUpdated
    static constraints = {
        description(blank: false)
    }
    static belongsTo = [createdBy:User]
    static hasMany = [ratings:ResourceRating,readingItems:ReadingItem]
    String toString(){
        return "${description}"
    }

}

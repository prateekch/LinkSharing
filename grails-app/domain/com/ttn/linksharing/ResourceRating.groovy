package com.ttn.linksharing
class ResourceRating {

    User createdBy
    int score
    //User user
    Resource resource
    static belongsTo = [resource:Resource,createdBy:User]
    static constraints = {
        createdBy(nullable: false)
        score(nullable:false,min: 1,max:5)
        resource(nullable: false,unique:'createdBy')
    }
}

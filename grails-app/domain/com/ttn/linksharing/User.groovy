package com.ttn.linksharing


class User {

    String email
    String password
    String firstname
    String lastname
    String username

    Byte[] photo
    boolean admin
    boolean active
    Date dateCreated
    Date lastUpdated



    static constraints = {
    email(unique: true,email: true,nullable: false,blank: false)
        password(nullable: false,blank: false,minSize: 5)
        firstname(nullable: false,blank: false)
        lastname(nullable: false,blank: false)
        photo(nullable: true)
        admin(nullable:true)
        active(nullable:true)
    }
    static hasMany = [topics :Topic,subscriptions :Subscription ,
                      readingItems:ReadingItem,resources :Resource]

    static mapping = {
        photo(sqlType:'longblob')
    }
    String getName(){
        [firstname,lastname].findAll{it}.join(' ')
    }
    String toString(){
        return "${username}"
    }
}

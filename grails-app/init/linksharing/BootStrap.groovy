package linksharing

import com.ttn.linksharing.DocumentResource
import com.ttn.linksharing.LinkResource
import com.ttn.linksharing.ReadingItem
import com.ttn.linksharing.Resource
import com.ttn.linksharing.ResourceRating
import com.ttn.linksharing.Subscription
import com.ttn.linksharing.Topic
import com.ttn.linksharing.User
import enums.Seriousness
import enums.Visibility

class BootStrap {

    def init = { servletContext ->
        persistingObjects()
        subscribeTopics()
        createReadingItems()
        createResourceRatings()

    }

    def persistingObjects() {
        List<User> users = []
        List<Topic> topics = []
        List<Resource> resources = []

        if (User.count == 0) {
            users = createUsers()
        } else {
            log.error("User already have records ")
        }

        if (Topic.count == 0) {
            createTopics(users)
        }

        if (Resource.count == 0) {
            resources = createResources()
            resources.each {
                if (it.save(flush: true, failOnError: true)) {
                    log.info "Saved resources"
                }
            }
        }
    }

    def createUsers() {
        User admin = new User(email: "prateek123@gmail.com", username:"prateek12",firstname: "prateek", lastname: "chaudhary", password: "12345",confirmpassword: "12345", admin: true, active: true)
        User user = new User(email: "sumit@gmail.com", username: "sumit12", firstname: "sumit", lastname: "sharma", password: "12345",confirmpassword: "12345", admin: false, active: true)

        if (admin.save(failOnError: true, flush: true)) {
            log.info "Admin saved successfully"
        } else {
            log.error "Error in saving Admin"
        }
        if (user.save(failOnError: true, flush: true)) {
            log.info "User saved Successfully"
        } else {
            log.error "Error in saving Successfully"
        }
        [admin, user]
    }

    def createTopics(List<User> users) {
        def x = 0
        users.each { User user ->
            (1..5).each {
                Topic topic = new Topic(topicName: "topic  ${it + x}", visibility: Visibility.PUBLIC, createdBy: user)
                topic.save(flush: true, failOnError: true)
                log.info("Saved topic")
            }
            x += 5
        }
    }

    List<Resource> createResources() {
        List<Resource> resources = []
        List<Topic> topics = Topic.getAll()

        topics.each { Topic topic ->
            LinkResource linkResource = new LinkResource(createdBy: topic.createdBy, description: topic.topicName, topic: topic, url: "http://www.google.com")
            LinkResource linkResource1 = new LinkResource(createdBy: topic.createdBy, description: topic.topicName, topic: topic, url: "http://www.yahoo.com")
            resources.add(linkResource)
            resources.add(linkResource1)

            DocumentResource documentResource = new DocumentResource(topic: topic, createdBy: topic.createdBy, description: topic.topicName, filepath: "/home/prateek/LinkSharing/grails-app/assets/defaultPassword")
            DocumentResource documentResource1 = new DocumentResource(topic: topic, createdBy: topic.createdBy, description: topic.topicName, filepath: "/home/prateek/LinkSharing/grails-app/assets/newfile")
            resources.add(documentResource)
            resources.add(documentResource1)
        }
        return resources

    }

    def subscribeTopics() {
        List<User> users = User.getAll()
        List<Topic> topics = Topic.getAll()
        users.each { User user ->
            topics.each { Topic topic ->
                if (!Subscription.findAllByUserAndTopic(user, topic)) {
                    Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.SERIOUS)
                    if (subscription.save(flush: true))
                        log.info "subscribed ${user.firstname} for ${topic.topicName}"
                    else
                        log.error "Not subscribed"
                }
            }
        }
    }

 def createReadingItems() {
        List<ReadingItem> readingItemList=[]
        List<Subscription> subscriptions=Subscription.getAll()
        subscriptions.each {Subscription subscription->
            Topic topic=subscription.topic
            List<Resource> resources=  Resource.findAllByTopic(topic)
            println(resources)
            resources.each {Resource resource->
                if(resource.createdBy!=subscription.user && ReadingItem.countByUserAndResource(subscription.user,resource)==0){
                    ReadingItem readingItem=new ReadingItem(user:subscription.user,isRead: false,resource: resource)
                    if(readingItem.save(flush:true,failOnError:true)){
                        log.info "saved reading item for ${subscription.user} for resource - ${resource}"
                    }
                }

            }

        }
    }
    def createResourceRatings(){
        List<ReadingItem> readingItems=ReadingItem.findAllByIsRead(false)
        readingItems.each{ReadingItem readingItem->
            ResourceRating resourceRating=new ResourceRating(score: 3,createdBy: readingItem.user,resource: readingItem.resource)
            if(resourceRating.save(flush:true,failOnError:true)){
                log.error "resourceRating saved for ${resourceRating.createdBy}"
            }

        }
    }
}
package com.ttn.linksharing

import enums.Visibility

class TopicController {

    def index() { }
    def  showAction(int id){
        id=1
        Topic topic=Topic.get(id)
        if(!topic){
            flash.error="Topic not found in database with ${id}"
            redirect(controller:'login')
        }
        else{
            if(topic.visibility==Visibility.PUBLIC){
              println("Success")
                render "success"
            }
            else{
                Subscription subscription=Subscription.findAllByUserAndTopic(session.user,topic)
                if(subscription){
                    render "success"
                }
                else{
                    flash.error="Subscription doesnt exist for ${topic} for ${session.user}"
                    redirect(controller: 'login')
                }
            }
        }
    }
}

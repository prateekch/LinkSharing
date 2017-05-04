package com.ttn.linksharing


class LoginController {

    def index() {
        def user=session["user"]
        if(user){
            forward controller:"User",action:"index"
        }
        else{
          render "failure"
        }

    }

    def loginHandler(){}

    def logoutAction(){}
}

package com.ttn.linksharing


class LoginController {

    def index() {
        if(session.user){
            forward controller:"user"
        }
        else{
          render "failure"
        }

    }

    def loginHandler(String username,String password) {
        username = "sumit12"
        password = "12345"
        User user = User.findByUsernameAndPassword(username, password)
            if (user.active) {
                session.user = user
            } else {
               render flash.error = 'Your account is not active'

            }
        }

    def registerAction(String firstname,String lastname,String email,String username,String password,String confirmPassword,boolean admin,boolean active){

        User user=new User(firstname: firstname,lastname: lastname,email: email,username: username,password: password,confirmpassword: confirmPassword,admin: admin,active: active)
        if(user.save(flush:true)){
              render "Success"
        }
        else{
            render flash.errors = user.errors.allErrors.collect { message(error: it) }
        }
    }
    def logoutAction(){
        session.invalidate()
        forward controller:"login"
    }
}

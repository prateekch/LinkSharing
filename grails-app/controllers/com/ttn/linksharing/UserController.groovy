package com.ttn.linksharing

class UserController {

    def index() {
        render "${session.user}"
    }

}

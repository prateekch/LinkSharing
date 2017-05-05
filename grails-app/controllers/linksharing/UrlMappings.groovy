package linksharing

import org.springframework.stereotype.Controller

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

/*

        "/test"( controller: "Test" , action:"noAction")

        "/helloTest"(redirect: '/test.test')

        "/redirectTest"(redirect: '/test')

        "/errorTest"(redirect:[controller:"test",action: 'save'])
*/

        "/"(controller: "login" )
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

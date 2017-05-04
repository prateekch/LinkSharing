package linksharing

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/test"( controller: "Test" , action:"noAction")

        "/helloTest"(redirect: '/test.test')

        "/redirectTest"(redirect: '/test')

        "/errorTest"(redirect:[controller:"test",action: 'save'])

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

package com.ttn.linksharing

class DocumentResource extends Resource{

    String filepath

    static constraints = {
      filepath(blank: false)
    }

    String toString(){
        return "${filepath}"
    }

}

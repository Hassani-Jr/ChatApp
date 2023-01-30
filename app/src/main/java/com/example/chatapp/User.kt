package com.example.chatapp

class User {
    var name: String? = null
    var email: String? = null
    var uID: String? = null

    constructor(){}

    constructor(name: String?, email: String?, uID: String?){
        this.name = name
        this.email = email
        this.uID = uID
    }
}
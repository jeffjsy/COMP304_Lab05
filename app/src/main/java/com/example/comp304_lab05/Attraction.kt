package com.example.comp304_lab05

class Attraction {
    public lateinit var name : String
    public var longitude : Double = 0.0
    public var latitude : Double = 0.0

    public constructor(name : String, latitude : Double, longitude : Double) {
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
    }
}

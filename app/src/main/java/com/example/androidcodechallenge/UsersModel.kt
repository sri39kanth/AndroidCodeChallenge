package com.example.androidcodechallenge

import androidx.annotation.Keep
import java.util.*

@Keep
class UsersModel {

    var gender: String? = null

    lateinit var name : Name

    lateinit var location : Location

    var email: String? = null

    lateinit var login : LoginData

    lateinit var dob: DateOfBirth

     lateinit var registered: Registered

    lateinit var id : IdValues

    lateinit var picture: PictureData

     var nat : String? = null



    data class Name constructor(  var title: String?, var first: String?, var last: String?)

    data class Location constructor( var street: Street, var city : String?, var state : String?,
                                     var country : String?, var postcode : String?, var coordinates: Coordinates,
                                     var timeZone: TimeZone)

    data class Street constructor( var number : String?, var name: String?)

    data class Coordinates constructor(var latitude: String?, var longitude: String?)

    data class TimeZone constructor(var offset : String?, var description : String?)

    data class LoginData constructor(var uuid : String?, var username: String?, var password: String?, var salt: String?,
    var mda: String?, var sha1 :String?, var sha256: String?)

    data class Registered constructor(var phone : String?, var cell : String?)

    data class DateOfBirth constructor(var date : Date, var age : String?)

    data class PictureData constructor(var large: String?, var medium : String?, var thumbnail: String?)

    data class IdValues constructor(var name: String?, var value : String?)
}
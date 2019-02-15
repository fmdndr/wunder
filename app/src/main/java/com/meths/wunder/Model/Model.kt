package com.meths.wunder.Model


class User(val gender: String, val name: Name, val location: UserLocation, val category: String ,
           val timezone: String ,val email: String , val login:Login, val dob:Dob,
           val registered:Registered, val phone:String, val cell:String , val id: ID ,
           val picture:Picture, val nat:String )


class Name(val title: String, val first: String, val last: String)
class UserLocation(val street: String, val city: String, val state: String, val postcode: Int, val coordinates: UserCoordinate )
class UserCoordinate(val latitude:Double, val longtitude: Double )
class Login(val uuid: String, val username:String, val password:String,val salt:String, val md5:String,val sha1:String,val sha256:String)
class Dob(val date:String, val age:Int)
class Registered(val date:String, val age:Int)
class ID(val name:String, val value:String?)
class Picture(val large:String, val medium: String, val thumnail:String)

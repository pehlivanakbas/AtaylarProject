package com.example.ataylarproject.Models

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

data class Site(

    var name: String = "",

    val adminId: String = "",

    val id: Int
)

data class Block(

    val name: String = "",

    val adminId: String = "",

    val siteId: String = "",

    val id: Int
)

data class Region(

    val name: String = "",

    val adminId: String = "",

    val blockId: String = "",

    val id: Int
)

data class Location(

    val name: String? = "",

    val adminId: String? = "",

    val regionId: String = "",

    val id: Int
)


data class Fault(
    val employeeId: Int =0,

    val locationId: String = "",

    val adminId: String = "",

    val employeeNote: String = "",

    val id: Int
)


data class User (
   
    val id: Int = 0,
    
    var adminId: String = "",

     
    var name: String = "",

    var phoneNumber : String= "",

     
    var occupation : String= "",

     
    var professionalChamber: String = "",

     
    var registerNo: String = "",

     
    var tcKimlikNo : String= "",

     
    var tcKimlikSeriNo: String = "",

     
    var sskNo : String = "",

     
    var ownPersonal : Boolean = true,

     
    var userRole: String = ""

)
data class SiteInfo(
    var id: Int,

    var siteId: String,

    var adress: String = "",

    var mail:String="",
    var phone: String=""

)


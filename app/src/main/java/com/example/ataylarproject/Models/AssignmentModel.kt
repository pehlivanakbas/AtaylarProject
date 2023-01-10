package com.example.ataylarproject.Models


//employeeId seçilen kişinen id si. location id seçilen bölgenin id si. admin id : Constants.AdmınId
data class Assignment(
    val id : Int = 0,
    val employeeId: Int,
    val locationId: Int,
    val adminId: Int
)

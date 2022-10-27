package com.example.ataylarproject.Models

data class Site(

    val name: String = "",

    val adminId: String = ""
)

data class Block(

    val name: String = "",

    val adminId: String = "",

    val siteId: String = ""

)

data class Region(

    val name: String = "",

    val adminId: String = "",

    val blockId: String = ""
)

data class Location(

    val name: String = "",

    val adminId: String = "",

    val regionId: String = ""
)

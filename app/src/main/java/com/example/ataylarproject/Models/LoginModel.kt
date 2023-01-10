package com.example.ataylarproject.Models

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginPostModel(
    @SerializedName("phoneNumber")
    var phoneNumber: String?,
    @SerializedName("password")
    var password: String?
)

data class PreLoginResponseModel(
    @SerializedName("newCustomer")
    val newCustomer: Boolean?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("errorMessage")
    val errorMessage: String?
)





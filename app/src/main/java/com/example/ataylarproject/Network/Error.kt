package com.example.ataylarproject.Network

import okhttp3.ResponseBody

data class ErrorModel(var name: ErrorType? = null,
                      var message: String? = "",
                      var code: Int = 500,
                      var errorBody: ResponseBody? = null) {
    init {
        this.name = name
        this.message = message
        this.code = code
        this.errorBody = errorBody
    }
}

enum class ErrorType {
    NETWORK_ERROR, SERVER_ERROR, NOT_FOUND_ERROR, NOT_ALLOWED_ERROR, FORBIDDEN_ERROR
}
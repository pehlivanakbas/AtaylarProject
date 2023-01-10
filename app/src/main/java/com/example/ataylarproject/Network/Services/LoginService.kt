package com.example.com.iottech.evedijitalandroid

import android.util.Log
import com.example.ataylarproject.Models.LoginPostModel
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.Network.ApiInterface
import com.example.ataylarproject.Network.ErrorHandler
import com.example.ataylarproject.Network.ErrorModel
import com.example.ataylarproject.Network.RetrofitClientBuilder


import retrofit2.Call
import retrofit2.Response

object LoginService {

    fun postLogin(
        requestBody: LoginPostModel,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<User>? = apiInterface?.login(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)            }

        })
    }


}

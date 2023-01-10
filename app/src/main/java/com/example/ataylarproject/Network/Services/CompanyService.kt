package com.example.ataylarproject.Network.Services

import android.util.Log
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.Company
import com.example.ataylarproject.Models.Site
import com.example.ataylarproject.Network.ApiInterface
import com.example.ataylarproject.Network.ErrorHandler
import com.example.ataylarproject.Network.ErrorModel
import com.example.ataylarproject.Network.RetrofitClientBuilder
import retrofit2.Call
import retrofit2.Response

object CompanyService {

    fun getCompanyByID(
        id: String,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Company>? = apiInterface?.getCompanyById(id)

        apiCall?.enqueue(object : retrofit2.Callback<Company> {

            override fun onResponse(
                call: Call<Company>,
                response: Response<Company>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<Company>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    fun saveCompany(
        requestBody: Company,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Company>? = apiInterface?.updateCompany(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Company> {



            override fun onResponse(
                call: Call<Company>,
                response: Response<Company>) {

                    val responseAny = response as Response<*>
                    when(response.code()) {
                        200 -> response.body()?.let {
                            success(it)
                        }
                        else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                    }

            }

            override fun onFailure(call: Call<Company>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)            }

        })
    }
    fun updateCompany(
        requestBody: Company,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Company>? = apiInterface?.updateCompany(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Company> {



            override fun onResponse(
                call: Call<Company>,
                response: Response<Company>) {

                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }

            }

            override fun onFailure(call: Call<Company>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)            }

        })
    }

}
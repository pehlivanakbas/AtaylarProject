package com.example.ataylarproject.Network.Services

import android.util.Log
import com.example.ataylarproject.Models.Fault
import com.example.ataylarproject.Models.LegislationModel
import com.example.ataylarproject.Models.Location
import com.example.ataylarproject.Network.ApiInterface
import com.example.ataylarproject.Network.ErrorHandler
import com.example.ataylarproject.Network.ErrorModel
import com.example.ataylarproject.Network.RetrofitClientBuilder
import retrofit2.Call
import retrofit2.Response

object LegislationService {
    fun createLegislation(
        requestBody: LegislationModel,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    ) {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall: Call<LegislationModel>? = apiInterface?.savelegislations(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<LegislationModel> {

            override fun onResponse(
                call: Call<LegislationModel>,
                response: Response<LegislationModel>
            ) {
                val responseAny = response as Response<*>
                when (response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<LegislationModel>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }


    fun getLegislations(
        stufe: Int,
        upperId: Int,
        success: (Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    ) {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall: Call<List<LegislationModel>>? =
            apiInterface?.getLegislationsByUpperId(upperId, stufe)

        apiCall?.enqueue(object : retrofit2.Callback<List<LegislationModel>> {

            override fun onResponse(
                call: Call<List<LegislationModel>>,
                response: Response<List<LegislationModel>>
            ) {
                val responseAny = response as Response<*>
                when (response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<List<LegislationModel>>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }


}
package com.example.ataylarproject.Network.Services

import android.util.Log
import com.example.ataylarproject.Constants
import com.example.ataylarproject.Models.*
import com.example.ataylarproject.Network.ApiInterface
import com.example.ataylarproject.Network.ErrorHandler
import com.example.ataylarproject.Network.ErrorModel
import com.example.ataylarproject.Network.RetrofitClientBuilder

import retrofit2.Call
import retrofit2.Response

object ProjectService {


    //sites
    fun createSite(
        requestBody: Site,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Site>? = apiInterface?.createSite(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Site> {

            override fun onResponse(
                call: Call<Site>,
                response: Response<Site>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<Site>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    fun getAllSites(
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<List<Site>>? = apiInterface?.getALlSites(Constants.ADMIN_ID)

        apiCall?.enqueue(object : retrofit2.Callback<List<Site>> {

            override fun onResponse(
                call: Call<List<Site>>,
                response: Response<List<Site>>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<List<Site>>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    //blocks

    fun createBlocks(
        requestBody: Site,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Block>? = apiInterface?.createBlock(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Block> {

            override fun onResponse(
                call: Call<Block>,
                response: Response<Block>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<Block>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    fun getAllBlocks(
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<List<Block>>? = apiInterface?.getALlBlocks(Constants.ADMIN_ID)

        apiCall?.enqueue(object : retrofit2.Callback<List<Block>> {

            override fun onResponse(
                call: Call<List<Block>>,
                response: Response<List<Block>>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<List<Block>>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    //regions

    fun createRegions(
        requestBody: Region,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Region>? = apiInterface?.createRegion(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Region> {

            override fun onResponse(
                call: Call<Region>,
                response: Response<Region>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<Region>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    fun getAllRegions(
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<List<Region>>? = apiInterface?.getALlRegions(Constants.ADMIN_ID)

        apiCall?.enqueue(object : retrofit2.Callback<List<Region>> {

            override fun onResponse(
                call: Call<List<Region>>,
                response: Response<List<Region>>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<List<Region>>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    //locations

    fun createLocations(
        requestBody: Location,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Location>? = apiInterface?.createLocation(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Location> {

            override fun onResponse(
                call: Call<Location>,
                response: Response<Location>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<Location>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    fun getAllLocations(
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<List<Location>>? = apiInterface?.getALlLocations(Constants.ADMIN_ID)

        apiCall?.enqueue(object : retrofit2.Callback<List<Location>> {

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    //sites
    fun createFault(
        requestBody:Fault,
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<Fault>? = apiInterface?.createFault(requestBody)

        apiCall?.enqueue(object : retrofit2.Callback<Fault> {

            override fun onResponse(call: Call<Fault>, response: Response<Fault>) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<Fault>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }

    fun getAllFaults(
        success: (success: Any) -> Unit,
        failure: (failure: ErrorModel) -> Unit
    )
    {
        val apiInterface = RetrofitClientBuilder.client?.create(ApiInterface::class.java)
        val apiCall : Call<List<Fault>>? = apiInterface?.getALlFaults(Constants.ADMIN_ID)

        apiCall?.enqueue(object : retrofit2.Callback<List<Fault>> {

            override fun onResponse(call: Call<List<Fault>>, response: Response<List<Fault>>) {
                val responseAny = response as Response<*>
                when(response.code()) {
                    200 -> response.body()?.let {
                        success(it)
                    }
                    else -> failure(ErrorHandler().returnResponse() as ErrorModel)
                }
            }

            override fun onFailure(call: Call<List<Fault>>, t: Throwable) {
                Log.d("AUTH_ERROR", t.toString())
                failure(ErrorHandler().returnResponse() as ErrorModel)
            }

        })
    }
}
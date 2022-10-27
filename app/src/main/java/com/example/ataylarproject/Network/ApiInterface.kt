package com.example.ataylarproject.Network

import com.example.ataylarproject.Models.Block
import com.example.ataylarproject.Models.Location
import com.example.ataylarproject.Models.Region
import com.example.ataylarproject.Models.Site
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("api/projects/site")
    fun createSite(@Body site: Site): Call<Site>

    @POST("api/projects/block")
    fun createBlock(@Body block: Block): Call<Block>

    @POST("api/projects/region")
    fun createRegion(@Body region: Region): Call<Region>

    @POST("api/projects/location")
    fun createLocation(@Body location: Location): Call<Location>

    @GET("api/projects/sites/{id}")
    fun getALlSites(@Path (value = "id",  encoded = true) id : String) : Call<List<Site>>

    @GET("api/projects/blocks/{id}")
    fun getALlBlocks(@Path (value = "id",  encoded = true) id : String) : Call<List<Block>>

    @GET("api/projects/regions/{id}")
    fun getALlRegions(@Path (value = "id",  encoded = true) id : String) : Call<List<Region>>

    @GET("api/projects/locations/{id}")
    fun getALlLocations(@Path (value = "id",  encoded = true) id : String) : Call<List<Location>>



}
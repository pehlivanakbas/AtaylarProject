package com.example.ataylarproject.Network

import com.example.ataylarproject.Models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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
    fun getALlSites(@Path(value = "id", encoded = true) id: String): Call<List<Site>>

    @GET("api/projects/blocks/{id}")
    fun getALlBlocks(@Path(value = "id", encoded = true) id: String): Call<List<Block>>

    @GET("api/projects/regions/{id}")
    fun getALlRegions(@Path(value = "id", encoded = true) id: String): Call<List<Region>>

    @GET("api/projects/locations/{id}")
    fun getALlLocations(@Path(value = "id", encoded = true) id: String): Call<List<Location>>

    @GET("api/faults/getAll/{adminId}")
    fun getALlFaults(@Path(value = "adminId", encoded = true) id: String): Call<List<Fault>>

    @GET("api/user/users/{adminId}")
    fun getAllUsers(@Path(value = "adminId", encoded = true) adminId: String): Call<List<User>>

    @POST("api/user/register")
    fun createUser(@Body user: User): Call<User>

    @GET("api/company/companies/{id}")
    fun getCompanyById(@Path("id") id: String): Call<Company>

    @POST("api/company/save")
    fun saveCompany(@Body body: Company): Call<Company>

    @PUT("api/company/update")
    fun updateCompany(@Body body: Company): Call<Company>

    @POST("api/user/login")
    fun login(@Body user: LoginPostModel): Call<User>

    @POST("api/save/site")
    fun saveSiteInfo(@Body siteInfo: SiteInfo): Call<SiteInfo>

    ///

    @POST("api/assignment/assign")
    fun addAssignment(@Body body: Assignment): Call<Assignment>

    @POST("api/faults/add")
    fun saveFault(@Body body: Fault): Call<Fault>

    @POST("api/legislations/save/legislation")
    fun savelegislations(@Body body: LegislationModel): Call<LegislationModel>

    @GET("api/legislations/get/legislations/{stufe}")
    fun getLegislationsByStufe(
        @Path(
            value = "stufe",
            encoded = true
        ) stufe: Int
    ): Call<List<LegislationModel>>

    @GET("api/legislations/get/legislations/{upperId}/{stufe}")
    fun getLegislationsByUpperId(
        @Path(value = "upperId", encoded = true) upperId: Int,
        @Path(value = "stufe", encoded = true) stufe: Int
    ): Call<List<LegislationModel>>


}
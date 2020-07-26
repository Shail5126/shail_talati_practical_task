package com.shail.talati.model

import retrofit2.Call
import retrofit2.http.GET

interface ListInterface {

    @GET("photos")
    fun getListDetails(): Call<List<Details>>

}
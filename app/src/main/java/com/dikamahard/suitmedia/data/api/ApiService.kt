package com.dikamahard.suitmedia.data.api

import com.dikamahard.suitmedia.data.response.ListUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getListUser(
        @Query("page") page: Int?,
        @Query("per_page") per_page: Int?
    ):Call<ListUserResponse>
}
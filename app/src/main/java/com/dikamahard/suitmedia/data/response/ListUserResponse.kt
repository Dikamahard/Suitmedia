package com.dikamahard.suitmedia.data.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("per_page")
    val per_page: Int,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("total_pages")
    val total_pages: Int,

    @field:SerializedName("data")
    val data: ArrayList<UserItem>

)

data class UserItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("first_name")
    val first_name: String,

    @field:SerializedName("last_name")
    val last_name: String,

    @field:SerializedName("avatar")
    val avatar: String
)

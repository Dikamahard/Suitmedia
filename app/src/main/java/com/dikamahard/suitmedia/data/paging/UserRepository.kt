package com.dikamahard.suitmedia.data.paging

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dikamahard.suitmedia.data.api.ApiService
import com.dikamahard.suitmedia.data.response.UserItem

class UserRepository(private val apiService: ApiService, private val context: Context) {

    fun getUser(): LiveData<PagingData<UserItem>> {

        return Pager(
            config = PagingConfig(pageSize = 6),
            pagingSourceFactory = {UserPagingSource(apiService, context)}
        ).liveData
    }
}
package com.dikamahard.suitmedia

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dikamahard.suitmedia.data.api.ApiConfig
import com.dikamahard.suitmedia.data.paging.UserRepository
import com.dikamahard.suitmedia.data.response.ListUserResponse
import com.dikamahard.suitmedia.data.response.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdViewModel(userRepo: UserRepository) : ViewModel() {

    private val _listUser = MutableLiveData<List<UserItem>>()
    val user: LiveData<PagingData<UserItem>> = userRepo.getUser().cachedIn(viewModelScope)


    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun getAllUser() {
        val client = ApiConfig.getApiService().getListUser(null, null)
        client.enqueue(object: Callback<ListUserResponse> {
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                val responseBody = response.body()
                if(response.isSuccessful) {
                    _listUser.value = responseBody?.data
                }else {
                    _toastMessage.value = response.message()
                    Log.e("THIRDVM", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                _toastMessage.value = t.message
            }

        })
    }
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirdViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService, context)
    }
}
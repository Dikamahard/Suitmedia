package com.dikamahard.suitmedia.data.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dikamahard.suitmedia.data.api.ApiService
import com.dikamahard.suitmedia.data.response.UserItem
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException

class UserPagingSource(private val apiService: ApiService, val context: Context) : PagingSource<Int, UserItem>() {


    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getListUser(position, params.loadSize).awaitResponse()

            if (responseData.isSuccessful) {
                val stories = responseData.body()?.data
                val prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1
                val nextKey = if (stories.isNullOrEmpty()) null else position + 1

                LoadResult.Page(
                    data = stories ?: emptyList(),
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }else {
                LoadResult.Error(IOException("Failed to load stories: ${responseData.code()}"))
            }

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}
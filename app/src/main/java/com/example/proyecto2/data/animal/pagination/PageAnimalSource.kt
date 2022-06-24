package com.example.proyecto2.data.animal.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.proyecto2.data.animal.Animal



class PageAnimalSource (private val repo: PageAnimalService) : PagingSource<Int, Animal>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Animal> {
        return try {
            val page = params.key ?: 1
            val nationResponse = repo.getAnimals(page)
            LoadResult.Page(
                data = nationResponse,
                prevKey = if (page == 1) null else page - 1,
                nextKey =  if (nationResponse.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Animal>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
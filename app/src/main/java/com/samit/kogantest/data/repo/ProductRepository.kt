package com.samit.kogantest.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.samit.kogantest.data.pagination.ProductPageDataSourceFactory
import com.samit.kogantest.data.pagination.ProductRemoteDataSource
import com.samit.kogantest.ui.model.ProductUI
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) {
    fun observeRemotePagedSets(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<ProductUI>> {
        val dataSourceFactory = ProductPageDataSourceFactory(
            remoteDataSource, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory, ProductPageDataSourceFactory.pagedListConfig()
        ).build()
    }

}
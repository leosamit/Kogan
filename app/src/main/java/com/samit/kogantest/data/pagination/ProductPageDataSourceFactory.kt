package com.samit.kogantest.data.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.samit.kogantest.ui.model.ProductUI
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ProductPageDataSourceFactory @Inject constructor(
    private val dataSource: ProductRemoteDataSource,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, ProductUI>() {

    private val liveData = MutableLiveData<ProductPageDataSource>()

    override fun create(): DataSource<Int, ProductUI> {
        val source = ProductPageDataSource(dataSource = dataSource, scope = scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 100

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }

}
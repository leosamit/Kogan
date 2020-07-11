package com.samit.kogantest.data.pagination

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.samit.kogantest.data.Result
import com.samit.kogantest.data.mapper.toProductUI
import com.samit.kogantest.ui.model.ProductUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class ProductPageDataSource constructor(
    private val dataSource: ProductRemoteDataSource,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, ProductUI>() {

    private fun fetchData(page: Int, callback: (List<ProductUI>) -> Unit) {

        scope.launch(getJobErrorHandler()) {
            val response = dataSource.fetchProducts(page)
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data!!.results.map { toProductUI(it) }
                callback(results.filter { it.category == "Air Conditioners" })
                Log.d(
                    "product fetch",
                    results.filter { it.category == "Air Conditioners" }.toString()
                )
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ProductUI>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ProductUI>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ProductUI>) {
    }
}
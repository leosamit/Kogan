package com.samit.kogantest.data.pagination

import com.samit.kogantest.api.BaseDataSource
import com.samit.kogantest.api.KoganService


class ProductRemoteDataSource constructor(private val service: KoganService) : BaseDataSource() {

    suspend fun fetchProducts(page: Int) =
        getResult { service.getProducts(page) }
}
package com.samit.kogantest.api

import com.samit.kogantest.api.ApiKeys.Companion.API_GET_PRODUCTS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KoganService {

    @GET("$API_GET_PRODUCTS/{page}")
    suspend fun getProducts(@Path("page") page: Int): Response<ResultsResponse<ProductAPI>>

    @GET("$API_GET_PRODUCTS/{page}")
    fun getProductsSync(@Path("page") page: Int): Response<ResultsResponse<ProductAPI>>
}
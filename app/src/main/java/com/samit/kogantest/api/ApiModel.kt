package com.samit.kogantest.api

import com.google.gson.annotations.SerializedName

data class ProductAPI(
    @SerializedName(ApiKeys.CATEGORY)
    val category: String? = null,
    @SerializedName(ApiKeys.TITLE)
    val title: String? = null,
    @SerializedName(ApiKeys.WEIGHT)
    val weight: String? = null,
    @SerializedName(ApiKeys.SIZE)
    val size: ProductSizeApi? = null
)

data class ProductSizeApi(
    @SerializedName(ApiKeys.WIDTH)
    val width: Double,
    @SerializedName(ApiKeys.LENGTH)
    val length: Double,
    @SerializedName(ApiKeys.HEIGHT)
    val height: Double
)


interface ApiKeys {
    companion object {
        const val CATEGORY = "category"
        const val TITLE = "title"
        const val WEIGHT = "weight"
        const val SIZE = "size"
        const val WIDTH = "width"
        const val LENGTH = "length"
        const val HEIGHT = "height"
        const val NEXT = "next"
        const val OBJECTS = "objects"
        const val API_GET_PRODUCTS = "products"
        const val ENDPOINT = "http://wp8m3he1wt.s3-website-ap-southeast-2.amazonaws.com/api/"
    }
}
package com.samit.kogantest.data.mapper

import com.samit.kogantest.api.ProductAPI
import com.samit.kogantest.api.ProductSizeApi
import com.samit.kogantest.ui.model.ProductSize
import com.samit.kogantest.ui.model.ProductUI

fun toProductUI(productAPI: ProductAPI): ProductUI =
    ProductUI(
        category = productAPI.category,
        title = productAPI.title,
        weight = productAPI.weight,
        size = productAPI.size?.let { toProductSizeUI(it) }
    )

fun toProductSizeUI(productAPI: ProductSizeApi): ProductSize =
    ProductSize(
        average = toProductCubicWeight(productAPI.width, productAPI.length, productAPI.height),
        length = productAPI.length,
        width = productAPI.width,
        height = productAPI.height
    )

fun toProductCubicWeight(width: Double, length: Double, height: Double): String = String.format(
    "%.2f",
    ((width / 100) * (height / 100) * (length / 100) * 250)
) + " kg"



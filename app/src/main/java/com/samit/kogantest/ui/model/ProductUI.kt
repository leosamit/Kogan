package com.samit.kogantest.ui.model

data class ProductUI(
    val category: String?,
    val title: String?,
    val weight: String?,
    val size: ProductSize?
)

data class ProductSize(
    val width: Double,
    val length: Double,
    val height: Double,
    val average: String?
)

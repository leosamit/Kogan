package com.samit.kogantest.api

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    @SerializedName(ApiKeys.NEXT)
    val next: String? = null,
    @SerializedName(ApiKeys.OBJECTS)
    val results: List<T>
)
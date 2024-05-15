package com.example.myapplication_java

import com.google.gson.annotations.SerializedName

data class RetrofitDTO(
    @SerializedName("param") val param: String
)

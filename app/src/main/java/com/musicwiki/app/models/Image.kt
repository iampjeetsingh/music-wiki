package com.musicwiki.app.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val url: String,
    val size: String
)
package com.musicwiki.app.models

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val url: String,
    val fulltrack: String
)
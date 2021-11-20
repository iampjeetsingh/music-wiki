package com.musicwiki.app.models

data class Tag(
    val count: Int,
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)
package com.musicwiki.app.models.responses

import com.musicwiki.app.models.responses.wrappers.TopTagsWrapper

data class TopTagsResponse(
    val toptags: TopTagsWrapper
)
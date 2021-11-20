package com.musicwiki.app.models.responses

import com.musicwiki.app.models.responses.wrappers.TopAlbumsWrapper

data class TopAlbumsResponse(
    val albums: TopAlbumsWrapper
)
package com.musicwiki.app.models.responses

import com.musicwiki.app.models.responses.wrappers.TopTracksWrapper

data class TopTracksResponse(
    val tracks: TopTracksWrapper
)
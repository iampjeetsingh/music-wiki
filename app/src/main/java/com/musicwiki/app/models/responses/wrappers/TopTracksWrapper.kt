package com.musicwiki.app.models.responses.wrappers

import com.musicwiki.app.models.Track

data class TopTracksWrapper(
    val track: List<Track>
)
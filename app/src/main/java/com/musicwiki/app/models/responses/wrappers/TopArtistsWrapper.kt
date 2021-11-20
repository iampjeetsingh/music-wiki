package com.musicwiki.app.models.responses.wrappers

import com.musicwiki.app.models.Artist

data class TopArtistsWrapper(
    val artist: List<Artist>
)
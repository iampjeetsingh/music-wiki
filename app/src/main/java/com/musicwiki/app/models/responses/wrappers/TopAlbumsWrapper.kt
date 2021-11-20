package com.musicwiki.app.models.responses.wrappers

import com.musicwiki.app.models.Album

data class TopAlbumsWrapper(
    val album: List<Album>
)
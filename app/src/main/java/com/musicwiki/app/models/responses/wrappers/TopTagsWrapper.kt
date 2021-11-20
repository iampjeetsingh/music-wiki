package com.musicwiki.app.models.responses.wrappers

import com.musicwiki.app.models.Tag

data class TopTagsWrapper(
    val tag: List<Tag>
)
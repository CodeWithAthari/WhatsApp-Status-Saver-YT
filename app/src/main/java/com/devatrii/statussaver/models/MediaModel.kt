package com.devatrii.statussaver.models

import java.io.Serializable

const val MEDIA_TYPE_IMAGE = "image"
const val MEDIA_TYPE_VIDEO = "video"

data class MediaModel(
    val pathUri: String,
    val fileName: String,
    val type: String = MEDIA_TYPE_IMAGE,
    var isDownloaded: Boolean = false
):Serializable
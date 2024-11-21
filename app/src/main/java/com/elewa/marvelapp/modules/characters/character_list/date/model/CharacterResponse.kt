package com.elewa.marvelapp.modules.characters.character_list.date.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val data: Data? = null
)

data class Data(
    val limit: Int,
    val results: List<CharacterModel>
)


data class CharacterModel(
    val id: String,
    val name: String,
    val thumbnail: Thumbnail,
    val description: String?,
)

data class Thumbnail(
    @SerializedName("path")
    var path: String? = null,
    @SerializedName("extension")
    var extension: String? = null
)

package com.example.apitest.model

import com.google.gson.annotations.SerializedName

data class Mounts(
    val bgm: String,
    val description: String,
    @SerializedName("enhanced_description")
    val enhancedDescription: String,
    val icon: String,
    val id: Int,
    val image: String,
    @SerializedName("item_id")
    val itemId: Int,
    val name: String,
    val patch: String,
    val tooltip: String
)
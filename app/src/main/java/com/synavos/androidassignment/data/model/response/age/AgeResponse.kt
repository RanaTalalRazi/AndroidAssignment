package com.synavos.androidassignment.data.model.response.age

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
data class AgeResponse(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,
    val age: Int,
    val count: Int,
    val name: String
)
package com.synavos.androidassignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.synavos.androidassignment.data.model.response.age.AgeResponse

@Dao
interface EventDao {

    @Insert
    suspend fun addUser(user: AgeResponse)

    @Query("Select * from user_table where :name = name")
    suspend fun checkUserExist(name: String): AgeResponse?

    @Query("Select * from user_table")
    fun getUserList(): List<AgeResponse>

}
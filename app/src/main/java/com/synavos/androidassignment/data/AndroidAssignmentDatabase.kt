package com.synavos.androidassignment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.synavos.androidassignment.data.dao.EventDao
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Database(entities = [AgeResponse::class], version = 1, exportSchema = false)
abstract class AndroidAssignmentDatabase : RoomDatabase() {

    abstract fun eventsDao(): EventDao

    class Callback @Inject constructor(
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            applicationScope.launch {
            }
        }
    }
}
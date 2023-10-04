package com.synavos.androidassignment.di

import android.app.Application
import androidx.room.Room
import com.synavos.androidassignment.data.AndroidAssignmentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


const val DB_NAME = "ticket_lake_database"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application,
                        callback: AndroidAssignmentDatabase.Callback
    ) = Room.databaseBuilder(app, AndroidAssignmentDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(completableJob: CompletableJob) = CoroutineScope(completableJob)

    @Provides
    fun provideApplicationSupervisorJob() = SupervisorJob()


}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
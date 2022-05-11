package com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.di

import android.app.Application
import androidx.room.Room
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.data.local.MovieAppDao
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.data.local.MovieAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: MovieAppDatabase.Callback): MovieAppDatabase = Room.databaseBuilder(
        application,
        MovieAppDatabase::class.java,
        "alpha_database"
    ).fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideMovieAppDao(db: MovieAppDatabase): MovieAppDao = db.getMovieAppDao()
}
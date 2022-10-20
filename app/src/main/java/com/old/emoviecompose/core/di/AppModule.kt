package com.old.emoviecompose.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.old.data.LocalMovieDataSource
import com.old.data.RepositoryImp
import com.old.domain.databasemanager.*
import com.old.domain.repository.ApiConstants
import com.old.domain.repository.MoviesRepository
import com.old.emoviecompose.domain.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val DATABASE_NAME = "movies_db"

    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: RepositoryImp): MoviesRepository = dataSource

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.URL_REQUEST)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext app: Context
    ): MovieDatabase = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun providesMovieLocalDataSource(
        localMovieDatasourceImpl: LocalMovieDataSource
    ): ILocalDataSource {
        return localMovieDatasourceImpl
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()

    @Provides
    @Singleton
    fun provideMovieDetailDao(db: MovieDatabase): MovieDetailDao = db.movieDetailDao()

    @Provides
    @Singleton
    fun provideTrailerDao(db: MovieDatabase): TrailerDao = db.trailerDao()
}
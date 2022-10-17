package com.old.data.di

import com.old.data.MovieRepositoryImp
import com.old.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object  DataModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(dataSource: MovieRepositoryImp.Network): MoviesRepository = dataSource
}
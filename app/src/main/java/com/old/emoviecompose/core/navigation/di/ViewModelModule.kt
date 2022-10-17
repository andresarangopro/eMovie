package com.old.emoviecompose.core.navigation.di


import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.core.navigation.EMovieRouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = EMovieRouteNavigator()
}
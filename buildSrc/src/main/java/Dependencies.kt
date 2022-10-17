package com.old.buildsrc

object Versions {
    const val compose_ui = "1.2.1"
    const val material = "1.1.1"
    const val activityCompose = "1.3.1"
    const val runtimeKt ="2.3.1"
    const val coreKtx = "1.7.0"
    const val versionCompiler= "1.3.1"

    const val jUnit = "4.13.2"
    const val androidxTest = "1.1.3"
    const val androidxTestEspresso = "3.4.0"
    const val hiltVersion = "1.0.0"
    const val navigationVersion = "2.4.2"
    const val material3Version = "1.0.0-alpha13"
}

object DebugDependencies{
    private const val kanaryVersion = "2.9.1"
    const val leakKanary="com.squareup.leakcanary:leakcanary-android:$kanaryVersion"
}

object Kotlin {

    object Coroutines {
        private const val version = "1.6.4"
        private const val coreVersion = "1.6.4"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coreVersion"
        const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coreVersion"
    }
}

object Retrofit{
    const val retrofitVersion = "2.9.0"
    const val okHttpLoggingInterceptorVersion = "4.9.0"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${retrofitVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${retrofitVersion}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${okHttpLoggingInterceptorVersion}"
}

object Hilt {
    private const val version = "2.42"

    const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    const val android = "com.google.dagger:hilt-android:$version"
    const val compiler = "com.google.dagger:hilt-compiler:$version"
    const val testing = "com.google.dagger:hilt-android-testing:$version"
}



object AppDependencies {


    //android ui
    private const val androidXCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val androidxLifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKt}"
    private const val androidxActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    private const val androidxCompose = "androidx.compose.ui:ui:${Versions.compose_ui}"
    private const val androidxComposeUI = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_ui}"
    private const val androidxComposeMaterials = "androidx.compose.material:material:${Versions.material}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltVersion}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigationVersion}"
    const val material = "androidx.compose.material3:material3:${Versions.material3Version}"
    const val materialWindowSize = "androidx.compose.material3:material3-window-size-class:${Versions.material3Version}"

    //test libs
    //testImplementation
    private const val jUnit = "junit:junit:${Versions.jUnit}"

    //androidTestImplementation
    private const val androidxTest = "androidx.test.ext:junit:${Versions.androidxTest}"
    private const val androidxTestEspresso =
        "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"
    private const val androidxComposeUITest = "androidx.compose.ui:ui-test-junit4:${Versions.compose_ui}"

    //debug
    //debugImplementation
    const val androidCompoeUITooling = "androidx.compose.ui:ui-tooling:${Versions.compose_ui}"
    const val androidComposeUiTestManifest ="androidx.compose.ui:ui-test-manifest:${Versions.compose_ui}"

    val appLibraries = arrayListOf<String>().apply {
        add(androidXCore)
        add(androidxLifeCycle)
        add(androidxActivity)
        add(androidxCompose)
        add(androidxComposeUI)
        add(androidxComposeMaterials)
        add(hiltNavigationCompose)
        add(navigation)
        add(material)
        add(materialWindowSize)
    }

    val coroutinesLibraries = arrayListOf<String>().apply {
        add(Kotlin.Coroutines.android)
        add(Kotlin.Coroutines.playServices)
        add(Kotlin.Coroutines.core)
    }

    val hiltLibraries = arrayListOf<String>().apply {
        add(Hilt.android)
    }

    val retrofitLibraries = arrayListOf<String>().apply {
        add(Retrofit.retrofit)
        add(Retrofit.retrofitGson)
        add(Retrofit.okHttpLoggingInterceptor)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(Hilt.compiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidxTest)
        add(androidxTestEspresso)
        add(Kotlin.Coroutines.test)
        add(Hilt.testing)
    }

    val debugLibraries = arrayListOf<String>().apply{
        add(androidCompoeUITooling)
        add(androidComposeUiTestManifest)
        add(DebugDependencies.leakKanary)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(jUnit)
    }
}
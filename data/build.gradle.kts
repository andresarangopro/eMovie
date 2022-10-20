plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val localTmdbApiKey: String? by lazy {
    com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir).getProperty("TMDB_API_KEY")
}

val remoteTmdbApiKey: String by lazy {
    System.getenv("TMDB_API_KEY").orEmpty()
}

val tmdbApiKey: String by lazy {
    if (localTmdbApiKey != null) localTmdbApiKey.orEmpty() else remoteTmdbApiKey
}

android {

    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk =AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

     implementation(project(mapOf("path" to ":domain")))
    implementationOwn(AppDependencies.coroutinesLibraries)
    implementationOwn(AppDependencies.retrofitLibraries)
    implementationOwn(AppDependencies.hiltLibraries)
}
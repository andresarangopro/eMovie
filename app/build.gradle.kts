import com.old.buildsrc.*

plugins {
    id("com.android.application")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    kotlin("android")
}


android {

    namespace = "com.old.emoviecompose"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.old.emoviecompose"
        minSdk = AppConfig.minSdk
        targetSdk =AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.versionCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))




    //app libs
    _implementation(AppDependencies.appLibraries)
    _implementation(AppDependencies.coroutinesLibraries)
    _implementation(AppDependencies.retrofitLibraries)
    _implementation(AppDependencies.hiltLibraries)
    //test libs
    _testImplementation(AppDependencies.testLibraries)
    _androidTestImplementation(AppDependencies.androidTestLibraries)

    kapt(Hilt.compiler)

}
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
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data")))

    //app libs
    implementationOwn(AppDependencies.appLibraries)
    implementationOwn(AppDependencies.coroutinesLibraries)
    implementationOwn(AppDependencies.retrofitLibraries)
    implementationOwn(AppDependencies.hiltLibraries)
    //test libs
    testImplementationOwn(AppDependencies.testLibraries)
    androidTestImplementationOwn(AppDependencies.androidTestLibraries)

    kapt(Hilt.compiler)

}
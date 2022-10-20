

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}


android {

    compileSdk = AppConfig.compileSdk


    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk =AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        consumerProguardFiles("consumer-rules.pro")
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
    testImplementation("junit:junit:4.12")
    _implementation(com.old.buildsrc.AppDependencies.coroutinesLibraries)
    _implementation(com.old.buildsrc.AppDependencies.retrofitLibraries)
    _implementation(com.old.buildsrc.AppDependencies.hiltLibraries)

    //room
    api(com.old.buildsrc.Room.roomLib)
    implementation(com.old.buildsrc.Room.roomktx)
    kapt(com.old.buildsrc.Room.roomCompiler)
}
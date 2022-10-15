object Versions {
    val compose_ui = "1.2.1"
    val material = "1.1.1"
    val activityCompose = "1.3.1"
    val runtimeKt ="2.3.1"
    val coreKtx = "1.7.0"
    const val versionCompiler= "1.3.1"

    val jUnit = "4.13.2"
    val androidxTest = "1.1.3"
    val androidxTestEspresso = "3.4.0"
}

object DebugDependencies{
    val kanaryVersion = "2.9.1"
    val leakKanary="com.squareup.leakcanary:leakcanary-android:$kanaryVersion"
}

object AppDependencies {

    //android ui
    private val androidXCore = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val androidxLifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.runtimeKt}"
    private val androidxActivity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    private val androidxCompose = "androidx.compose.ui:ui:${Versions.compose_ui}"
    private val androidxComposeUI = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_ui}"
    private val androidxComposeMaterials = "androidx.compose.material:material:${Versions.material}"

    //test libs
    //testImplementation
    private val jUnit = "junit:junit:${Versions.jUnit}"

    //androidTestImplementation
    private val androidxTest = "androidx.test.ext:junit:${Versions.androidxTest}"
    private val androidxTestEspresso =
        "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"
    private val androidxComposeUITest = "androidx.compose.ui:ui-test-junit4:${Versions.compose_ui}"

    //debug
    //debugImplementation
    private val androidCompoeUITooling = "androidx.compose.ui:ui-tooling:${Versions.compose_ui}"
    private val androidComposeUiTestManifest ="androidx.compose.ui:ui-test-manifest:${Versions.compose_ui}"

    val appLibraries = arrayListOf<String>().apply {
        add(androidXCore)
        add(androidxLifeCycle)
        add(androidxActivity)
        add(androidxCompose)
        add(androidxComposeUI)
        add(androidxComposeMaterials)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(androidxTest)
        add(androidxTestEspresso)
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
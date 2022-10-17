import org.gradle.api.artifacts.dsl.DependencyHandler

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler._kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler._implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler._androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler._testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler._debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation ", dependency)
    }
}

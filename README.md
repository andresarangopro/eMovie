# Architecture Components
[![Language](https://img.shields.io/badge/Language-Kotlin%201.7-green)](https://developer.android.com/kotlin?gclid=CjwKCAjwtdeFBhBAEiwAKOIy55ieWxLXtWidj5YnTw364KCyEj8WDC20Fu-Jgq8yckexge58KMNgwRoCXQYQAvD_BwE&gclsrc=aw.ds)

[![Framework](https://img.shields.io/badge/Framework-Android%20Studio-blue)](https://developer.android.com/studio?gclid=CjwKCAjwtdeFBhBAEiwAKOIy52mw_xYp6g53m4PHlqEt9g4vckcNL16ylXAxdM8r4RY-yENilB4SrBoCDw0QAvD_BwE&gclsrc=aw.ds)



## How it looks

<p align="center">
    <img width="270" height="555" src="gitFiles/emoviemp4.gif">
</p>
  


## Architecture  

There are three main layers

Data: module responsible for Network and Database

Presentation: this module has all android framework using MVVM with jetpack composables and view models

Domain: module responsible for Use Cases, entities, Repository



![alt text](gitFiles/arquitecture.png)



## Troubleshooting
  
## API
This project implements TMDB's API. More about in:
https://www.themoviedb.org/

add on local.properties yourt TMDB Api v4 key as: 
TMDB_API_KEY= <YOUR_API_KEY>


  
## Libraries Used :


* [Jetpack Compose](https://developer.android.com/jetpack/compose/tutorial)

* [Materials 3](https://developer.android.com/jetpack/androidx/releases/compose-material3)

* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)

* [Animated Navigation](https://google.github.io/accompanist/navigation-animation/)

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=Cj0KCQiA4feBBhC9ARIsABp_nbVSzmSdBQuAKP2WhE9fTRDmz2u67AtgL7wFOrs5kgcNKuqHWPbA3mEaAsSJEALw_wcB&gclsrc=aw.ds)

* [Flow](https://developer.android.com/kotlin/coroutines/additional-resources)

* [Retrofit2](https://square.github.io/retrofit/)

* [Coroutines](https://developer.android.com/kotlin/coroutines)

* [Mockk](https://github.com/mockk/mockk)

* [Kluent](https://github.com/MarkusAmshove/Kluent)

* [RoboElectric](https://github.com/robolectric/robolectric)

* [Gradle - DSL ](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

* [Leak Canary](https://github.com/square/leakcanary)

* [Dagger Hilt](https://mvnrepository.com/artifact/com.google.dagger/hilt-android)

* [Room](https://developer.android.com/training/data-storage/room)

* [Coil](https://coil-kt.github.io/coil/compose/) 

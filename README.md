# Compose Exercise: Room CRUD
- Scaffold with TopAppBar and FAB.
- Set title dynamically from different screens by raising event.
- Turn on/off FAB in different screens by raising event.
- Show alert dialog to confirm an action.

## Dependency
```kt
ext {
    compose_version = '1.0.1'
    room_version = '2.3.0'
}

dependencies {
    classpath "com.android.tools.build:gradle:7.0.0"
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21"
}
```

```kt
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
}

// Compose Navigation
implementation "androidx.navigation:navigation-compose:2.4.0-alpha05"

// Room
implementation "androidx.room:room-runtime:$room_version"
kapt "androidx.room:room-compiler:$room_version"

// Optional - Kotlin Extensions and Coroutines support for Room
implementation "androidx.room:room-ktx:$room_version"

// Compose ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"

// Compose LiveData (StackOverflow)
implementation "androidx.compose.runtime:runtime-livedata:$compose_version"

// LiveData (Flow to LiveData: asLiveData)
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
```

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

<br />

| Item List | Edit Item |
|:---: | :---:|
|![list](https://user-images.githubusercontent.com/67064997/129905189-628faa43-df63-40f8-b9c1-37ff41d56356.png) | ![edit](https://user-images.githubusercontent.com/67064997/129905197-73cfdb03-dfdc-4c1c-9c19-4258a948595f.png)|
| Add Item | Item Details |
|![add](https://user-images.githubusercontent.com/67064997/129905201-ec2fe7a4-0d74-4770-b32f-ac238440cca1.png) | ![sell](https://user-images.githubusercontent.com/67064997/129905203-b3836041-6dbf-4b19-84e6-12ba31bd0f51.png)|

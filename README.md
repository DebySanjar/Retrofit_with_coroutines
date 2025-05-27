# 📋 Retrofit + Coroutine based Todo App (MVVM Architecture)

This is a simple **Todo Android application** built using **Kotlin**, **Retrofit**, **Coroutines**, and **MVVM** architecture.  
The app supports basic **CRUD operations** using a RESTful API.

---

## 🚀 Features

- ✅ Fetch list of todos from API (GET)
- ✅ Add new todo (POST)
- ✅ Delete todo (DELETE)
- ✅ Live progress indication
- ✅ MVVM + Repository architecture
- ✅ ViewModel & LiveData for UI state handling
- ✅ Clean separation of concerns

---

## 🧰 Tech Stack

| Layer           | Technology                                 |
|----------------|---------------------------------------------|
| UI             | XML, ViewBinding, RecyclerView              |
| State          | ViewModel, LiveData                         |
| Networking     | Retrofit, Gson, Coroutines (Dispatchers.IO) |
| Architecture   | MVVM + Repository Pattern                   |

---

uses api for retrofit :
```gradle
https://todoeasy.pythonanywhere.com/
```

## 📦 Dependencies

```kotlin
// Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1'

// Lifecycle
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'

// Material Design
implementation 'com.google.android.material:material:1.9.0'
```

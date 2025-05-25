plugins {
    id("com.android.application")
    id("com.google.gms.google-services") // Ensure this is included
}

android {
    namespace = "com.example.bustracker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bustracker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gms.play.services.maps)
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.preference)
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // UI & Animation
    implementation("com.airbnb.android:lottie:6.0.0")
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.intuit.ssp:ssp-android:1.1.1")

    // Networking
    implementation("com.android.volley:volley:1.2.1")

    // Firebase BOM (Ensures all Firebase dependencies are compatible)
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    // Firebase Authentication
    implementation("com.google.firebase:firebase-auth")

    // Firebase Realtime Database
    implementation("com.google.firebase:firebase-database")

    // Google Play Services (Location)
    implementation("com.google.android.gms:play-services-location:21.1.0")
    //maps
    implementation("com.google.android.gms:play-services-maps:18.0.2")

    //directions
    implementation ("com.google.maps:google-maps-services:0.18.0")

    implementation ("com.google.maps.android:android-maps-utils:2.3.0")



    // OpenStreetMap Library
    implementation("org.osmdroid:osmdroid-android:6.1.16")

    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson for JSON parsing
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Core
    implementation("androidx.core:core:1.12.0")

    //location
    implementation ("com.google.android.gms:play-services-location:18.0.0")


    //okhttp
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1");

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}

// build.gradle.kts (Module: app)
plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.example.bustracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bustracker"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    // Remove Google Maps dependency:
    // implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // UI & Animation
    implementation("com.airbnb.android:lottie:6.0.0")
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.intuit.ssp:ssp-android:1.1.1")

    // Networking
    implementation("com.android.volley:volley:1.2.1")

    // Firebase
    implementation ("com.google.firebase:firebase-database:20.2.1")
    implementation ("com.google.firebase:firebase-auth:22.1.2")
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    implementation ("org.osmdroid:osmdroid-android:6.1.16")

    implementation ("com.google.android.gms:play-services-location:21.1.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //Gson
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //core
    implementation("androidx.core:core:1.12.0")
}

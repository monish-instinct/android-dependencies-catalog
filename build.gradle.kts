buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath libs.gradle.v820
        classpath "com.android.tools.build:manifest-merger:30.3.1"
    }
}

plugins {
//    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'org.jetbrains.kotlin.plugin.compose' version '2.1.21'
}

apply plugin: 'com.android.library'

configurations {
    embed {
        canBeConsumed = false
        canBeResolved = true
        transitive = true  // Include nested dependencies
    }
    implementation.extendsFrom(embed)
}

android {
    namespace 'skynetbee.developers.DevEnvironment'
    compileSdk 36

    defaultConfig {
        minSdk 24
        targetSdk 35
        consumerProguardFiles 'consumer-rules.pro'
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
        debug {
            matchingFallbacks = ['release']
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

//    packagingOptions {
//        excludes += [
//                "META-INF/*.kotlin_module",
//                "META-INF/LICENSE*",
//                "META-INF/AL2.0",
//                "META-INF/LGPL2.1",
//                "**/R.class",
//                "**/R\$*.class"  // Exclude R classes from dependencies
//        ]
//    }
}

dependencies {

//    compileOnly "androidx.compose.ui:ui-text-android:1.7.0"

    // ✅ Use implementation for standard/shared libraries
    implementation libs.androidx.core.ktx
    implementation libs.androidx.lifecycle.runtime.ktx
    implementation libs.androidx.lifecycle.livedata.ktx
    implementation libs.androidx.appcompat
    implementation libs.androidx.activity.compose
    implementation platform(libs.androidx.compose.bom)
    implementation libs.androidx.compose.ui
    implementation libs.androidx.compose.ui.tooling
    implementation libs.androidx.constraintlayout.compose
    implementation libs.accompanist.systemuicontroller
    implementation libs.accompanist.flowlayout
    implementation libs.androidx.navigation.compose
    implementation libs.androidx.lifecycle.viewmodel.compose
    implementation libs.androidx.material3.v121
    implementation libs.kotlin.stdlib
    implementation(libs.androidx.media3.ui) // ✅ already here — keep it as implementation

    // 🟩 implementation only unique or not typically included libraries
    implementation libs.androidx.material.icons.extended.v177
    implementation libs.coil.compose
    implementation libs.lottie.compose
    implementation libs.android.database.sqlcipher
    implementation libs.androidx.sqlite
    implementation libs.okhttp
    implementation libs.itext7.core
    implementation libs.poi.ooxml
    implementation libs.checkout
    implementation libs.commons.lang3
    implementation libs.gson
    implementation libs.rxjava
    implementation libs.rxandroid
    implementation libs.androidx.media3.exoplayer

    // ✅ Testing-only libraries — do NOT implementation
    testImplementation libs.junit
    androidTestImplementation libs.androidx.junit
    androidTestImplementation libs.androidx.espresso.core
    androidTestImplementation platform(libs.androidx.compose.bom)
    androidTestImplementation libs.androidx.ui.test.junit4
    debugImplementation libs.androidx.ui.tooling
    debugImplementation libs.androidx.ui.test.manifest
}
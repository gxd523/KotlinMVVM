plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(30)
    }
}

dependencies {
    implementation(project(mapOf("path" to ":bean")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
}
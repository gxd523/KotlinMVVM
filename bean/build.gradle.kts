import com.build.plugin.AndroidConfig
import com.build.plugin.Dependency

plugins {
    id("com.android.library")
    id("com.build.plugin")
    kotlin("android")
}

android {
    compileSdkVersion(AndroidConfig.compileVersion)
    buildToolsVersion(AndroidConfig.buildVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minVersion)
        targetSdkVersion(AndroidConfig.targetVersion)
    }
}

dependencies {
    implementation(Dependency.Kotlin.stdlib)
}
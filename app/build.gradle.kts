import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.build.plugin.Dependency

plugins {
    kotlin("kapt")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("../kotlinSign.pfx")
            storePassword = "android"
            keyAlias = "android"
            keyPassword = "android"
        }
        getByName("debug") {
            storeFile = file("../kotlinSign.pfx")
            storePassword = "android"
            keyAlias = "android"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("release") {
            proguardFiles("proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            proguardFiles("proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    android.applicationVariants.all {
        outputs.all {
            if (this is ApkVariantOutputImpl) {
                this.outputFileName = "app_${versionName}_${buildType.name}.apk"
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":model")))
    implementation(project(mapOf("path" to ":bean")))
    // kotlin
    implementation(Dependency.Kotlin.stdlib)
    implementation(Dependency.Kotlin.coroutine)
    implementation(Dependency.Kotlin.coroutineAndroid)
    // jetpack
    implementation(Dependency.AndroidX.Arch.Lifecycle.runtime)
    implementation(Dependency.AndroidX.Arch.Lifecycle.viewModel)
    implementation(Dependency.AndroidX.Arch.Lifecycle.liveData)
    implementation(Dependency.AndroidX.Core.core)
    implementation(Dependency.AndroidX.Core.appcompat)
    implementation(Dependency.AndroidX.Ui.constraintLayout)
    implementation(Dependency.AndroidX.Ui.recyclerView)
    implementation(Dependency.AndroidX.Ui.viewpager2)
    implementation(Dependency.AndroidX.Core.fragment)
    implementation(Dependency.AndroidX.Navigation.ui)
    implementation(Dependency.AndroidX.Navigation.fragment)
    implementation(Dependency.Google.material)
    // thirdlib
    implementation(Dependency.Third.glide)
    kapt(Dependency.Third.glideCompiler)

    implementation(Dependency.Third.gson)
    implementation(Dependency.Third.okhttp)
    // 七牛云DNS优化
    implementation(Dependency.Third.qiNiuDns)
    implementation(Dependency.Third.pinyin)
}
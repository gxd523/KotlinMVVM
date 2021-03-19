package com.build.plugin

object Dependency {
    object AndroidX {
        const val multiDex = "androidx.multidex:multidex:2.0.0"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
        const val paging = "androidx.paging:paging-runtime-ktx:2.1.2"

        object Core {
            const val core = "androidx.core:core-ktx:1.3.2"
            const val appcompat = "androidx.appcompat:appcompat:1.2.0"
            const val activity = "androidx.activity:activity-ktx:1.1.0"
            const val fragment = "androidx.fragment:fragment-ktx:1.2.5"
        }

        object Arch {
            object Lifecycle {
                const val lifecycleVersion = "2.2.0"
                const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
                const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
                const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
            }
        }

        object Ui {
            const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
            const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        }

        object Navigation {
            private const val navigationVersion = "2.2.2"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
            const val ui = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:1.2.1"
    }

    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutineVersion}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutineVersion}"
    }

    object Third {
        const val glideVersion = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
        const val gson = "com.google.code.gson:gson:2.8.6"
        const val okhttp = "com.squareup.okhttp3:okhttp:3.9.1"
        const val qiNiuDns = "com.qiniu:happy-dns:0.2.13"
        const val pinyin = "com.github.promeg:tinypinyin:2.0.3"
    }

    object Room {
        private const val roomVersion = "2.2.5"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }
}
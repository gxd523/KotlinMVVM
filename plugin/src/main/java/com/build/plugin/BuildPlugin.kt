package com.build.plugin

import com.android.build.gradle.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.build.plugin.Testing.androidTestImplementation
import com.build.plugin.Testing.testImplementation

class BuildPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.config(project)
    }

    private fun PluginContainer.config(project: Project) {
        whenPluginAdded {
            when (this) {
                is AppPlugin -> {// app
                    //公共插件
                    project.configCommonPlugin()
                    //公共 android 配置项
                    project.extensions.getByType<AppExtension>().applyAppCommons(project)
                    //公共依赖
                    project.configAppDependencies()
                }
                is LibraryPlugin -> {// lib
                    //公共插件
                    project.configCommonPlugin()
                    //公共 android 配置项
                    project.extensions.getByType<LibraryExtension>().applyLibraryCommons(project)
                    //公共依赖
                    project.configLibraryDependencies()
                }
                is KotlinAndroidPluginWrapper -> {
                    //根据 project build.gradle.kts 中的配置动态设置 kotlinVersion
                    project.getKotlinPluginVersion()?.also { kotlinVersion ->
                        Version.kotlinVersion = kotlinVersion
                    }
                }
            }
        }
    }

    /**
     * library Module 公共依赖
     */
    private fun Project.configLibraryDependencies() {
        dependencies.apply {
            add("api", fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add("implementation", Dependency.Kotlin.stdlib)
            configTestDependencies()
        }
    }

    /**
     * app Module 公共依赖
     */
    private fun Project.configAppDependencies() {
        dependencies.apply {
            add("implementation", fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
            add("implementation", Dependency.Kotlin.stdlib)
            // 统一引入 baselib
            add("implementation", (project(":bean")))
            configTestDependencies()
        }
    }

    /**
     * test 依赖配置
     */
    private fun DependencyHandler.configTestDependencies() {
        testImplementation(Testing.testLibraries)
        androidTestImplementation(Testing.androidTestLibraries)
    }

    /**
     * kotlin 插件
     */
    private fun Project.configCommonPlugin() {
        plugins.apply("kotlin-android")
        plugins.apply("kotlin-android-extensions")
    }

    /**
     * app Module 配置项，此处固定了 applicationId
     */
    private fun AppExtension.applyAppCommons(project: Project) {
        defaultConfig { applicationId = AndroidConfig.applicationId }
        applyBaseCommons(project)
    }

    /**
     * library Module 配置项
     */
    private fun LibraryExtension.applyLibraryCommons(project: Project) {
        applyBaseCommons(project)

        /*      onVariants.withBuildType("debug") {
                  androidTest {
                      enabled = false
                  }
              }*/
    }

    private fun BaseExtension.applyBaseCommons(project: Project) {
        compileSdkVersion(AndroidConfig.compileVersion)

        defaultConfig {
            minSdkVersion(AndroidConfig.minVersion)
            targetSdkVersion(AndroidConfig.targetVersion)
            versionCode = AndroidConfig.versionCode
            versionName = AndroidConfig.versionName
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        project.tasks.withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
}
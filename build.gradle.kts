buildscript {
    val kotlinVersion by extra("1.4.31")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}

plugins {// 为了让build.gradle能找到我们的plugin需要在此声明一下，但是并不使用
    id("com.build.plugin") apply false
}

subprojects {// 为每个module配置内容
    project.apply(plugin = "com.build.plugin")
    if ("app" == project.name)
        project.apply(plugin = "com.android.application")
    else
        project.apply(plugin = "com.android.library")
}

//configurations.all {
//    val useLocal: String by project
//    if(useLocal.toBoolean()) {
//        resolutionStrategy.dependencySubstitution{
//            substitute(module("com.bennyhuo.tieguanyin:tieguanyin-runtime"))
//                .because("使用本地调试")
//                .with(project(":common"))
//        }
//    }
//}
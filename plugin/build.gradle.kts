buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    }
}
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    compileOnly(gradleApi())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
    compileOnly("com.android.tools.build:gradle:4.1.1")
}

gradlePlugin {
    plugins {
        create("version") {
            id = "com.build.plugin"
            implementationClass = "com.build.plugin.BuildPlugin"
        }
    }
}
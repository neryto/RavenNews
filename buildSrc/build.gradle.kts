import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven {
        url = uri("https://storage.googleapis.com/r8-releases/raw")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("com.android.tools:r8:8.1.54")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}

gradlePlugin {
    plugins {
        register("app-plugin") {
            id = "app-plugin"
            implementationClass = "AppPlugin"
        }

        register("module-plugin") {
            id = "module-plugin"
            implementationClass = "ModulePlugin"
        }
    }
}

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

kotlin {
    android {
        namespace = "${providers.gradleProperty("project.group").get()}.${providers.gradleProperty("project.name").get()}.logic"

        compileSdk {
            version = release(providers.gradleProperty("project.sdk.android.compile").get().toInt())
        }
        minSdk = providers.gradleProperty("project.sdk.android.min").get().toInt()

        withHostTest {}

        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(providers.gradleProperty("project.sdk.java.target").get())
        }
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget(providers.gradleProperty("project.sdk.java.target").get())
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

kotlin {
    android {
        namespace = "${providers.gradleProperty("project.group").get()}.${providers.gradleProperty("project.name").get()}.ui"

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

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":sharedLogic"))

            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
        }
    }
}

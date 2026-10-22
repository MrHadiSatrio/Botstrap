import com.hadisatrio.libs.gradle.module.ModuleName

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

kotlin {
    macosArm64 {
        binaries.executable {
            entryPoint = "${ModuleName(project).packageName}.main"
        }
    }

    sourceSets {
        macosMain.dependencies {
            implementation(project(":lib-cmp-greeting"))

            implementation(libs.compose.ui)
        }
    }
}

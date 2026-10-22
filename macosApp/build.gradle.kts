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
            entryPoint = "${providers.gradleProperty("project.group").get()}.${providers.gradleProperty("project.name").get()}.main"
        }
    }

    sourceSets {
        macosMain.dependencies {
            implementation(project(":lib-cmp-greeting"))

            implementation(libs.compose.ui)
        }
    }
}

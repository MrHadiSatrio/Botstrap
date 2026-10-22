import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(providers.gradleProperty("project.sdk.java.target").get())
    }
}

java {
    targetCompatibility = JavaVersion.toVersion(providers.gradleProperty("project.sdk.java.target").get())
}

compose.desktop {
    application {
        mainClass = "${providers.gradleProperty("project.group").get()}.${providers.gradleProperty("project.name").get()}.MainKt"
    }
}

dependencies {
    implementation(project(":sharedUI"))

    implementation(compose.desktop.currentOs)
}

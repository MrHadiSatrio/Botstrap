import com.hadisatrio.libs.gradle.semver.LiteralSemver
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.ruler)
}

android {
    namespace = "${project.properties["project.group"]}.${project.properties["project.name"]}"

    compileSdk {
        version = release(project.properties["project.sdk.android.compile"].toString().toInt())
    }

    defaultConfig {
        applicationId = "${project.properties["project.group"]}.${project.properties["project.name"]}"
        minSdk = project.properties["project.sdk.android.min"].toString().toInt()
        targetSdk = project.properties["project.sdk.android.target"].toString().toInt()
        versionCode = LiteralSemver(project.properties["project.version"].toString()).toInt()
        versionName = project.properties["project.version"].toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        val sourceInt = project.properties["project.sdk.java.source"].toString().toInt()
        val targetInt = project.properties["project.sdk.java.target"].toString().toInt()
        sourceCompatibility = JavaVersion.values()[sourceInt.dec()]
        targetCompatibility = JavaVersion.values()[targetInt.dec()]
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        val targetInt = project.properties["project.sdk.java.target"].toString().toInt()
        jvmTarget = JvmTarget.values()[targetInt - 8]
    }
}

ruler {
    abi.set("arm64-v8a")
    locale.set("en")
    screenDensity.set(422)
    sdkVersion.set(project.properties["project.sdk.android.target"].toString().toInt())
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

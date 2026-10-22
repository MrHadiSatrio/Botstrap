import com.hadisatrio.libs.gradle.module.ModuleName
import com.hadisatrio.libs.gradle.semver.LiteralSemver

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

android {
    namespace = ModuleName(project).packageName

    compileSdk {
        version = release(providers.gradleProperty("project.sdk.android.compile").get().toInt())
    }

    defaultConfig {
        applicationId = ModuleName(project).packageName
        minSdk = providers.gradleProperty("project.sdk.android.min").get().toInt()
        targetSdk = providers.gradleProperty("project.sdk.android.target").get().toInt()
        versionCode = LiteralSemver(providers.gradleProperty("project.version").get()).toInt()
        versionName = providers.gradleProperty("project.version").get()

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
        val sourceInt = providers.gradleProperty("project.sdk.java.source").get().toInt()
        val targetInt = providers.gradleProperty("project.sdk.java.target").get().toInt()
        sourceCompatibility = JavaVersion.values()[sourceInt.dec()]
        targetCompatibility = JavaVersion.values()[targetInt.dec()]
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":lib-cmp-greeting"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

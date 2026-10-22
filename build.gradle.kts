import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit
import kotlinx.kover.gradle.plugin.dsl.GroupingEntityType
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootEnvSpec
import org.jetbrains.kotlin.gradle.targets.wasm.binaryen.BinaryenEnvSpec
import org.jetbrains.kotlin.gradle.targets.wasm.binaryen.BinaryenPlugin
import org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsEnvSpec
import org.jetbrains.kotlin.gradle.targets.wasm.nodejs.WasmNodeJsPlugin
import org.jetbrains.kotlin.gradle.targets.wasm.yarn.WasmYarnPlugin
import org.jetbrains.kotlin.gradle.targets.wasm.yarn.WasmYarnRootEnvSpec

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.dokka)
}

dependencies {
    dokka(project(":app-android-greeting"))
    dokka(project(":app-jvm-greeting"))
    dokka(project(":macosApp"))
    dokka(project(":lib-kmp-greeting"))
    dokka(project(":lib-cmp-greeting"))
    dokka(project(":webApp"))
}

allprojects {
    group = providers.gradleProperty("project.group").get()
    version = providers.gradleProperty("project.version").get()

    // The settings declare the repositories that these tools download from.
    plugins.withType<NodeJsPlugin> { the<NodeJsEnvSpec>().downloadBaseUrl.set(null as String?) }
    plugins.withType<YarnPlugin> { the<YarnRootEnvSpec>().downloadBaseUrl.set(null as String?) }
    @OptIn(ExperimentalWasmDsl::class)
    plugins.withType<BinaryenPlugin> { the<BinaryenEnvSpec>().downloadBaseUrl.set(null as String?) }
    @OptIn(ExperimentalWasmDsl::class)
    plugins.withType<WasmNodeJsPlugin> { the<WasmNodeJsEnvSpec>().downloadBaseUrl.set(null as String?) }
    @OptIn(ExperimentalWasmDsl::class)
    plugins.withType<WasmYarnPlugin> { the<WasmYarnRootEnvSpec>().downloadBaseUrl.set(null as String?) }
}

subprojects {
    val libs = rootProject.libs

    apply(plugin = libs.plugins.detekt.get().pluginId)
    extensions.configure<DetektExtension> {
        autoCorrect = true
        buildUponDefaultConfig = true
        config.setFrom(rootProject.layout.projectDirectory.file("detekt.yml"))
        source.setFrom(fileTree("src") { include("*/java/**", "*/kotlin/**") })
    }
    dependencies {
        add("detektPlugins", libs.detekt.formatting)
    }

    apply(plugin = libs.plugins.kover.get().pluginId)
    extensions.configure<KoverProjectExtension> {
        val minBranchCoverage = providers.gradleProperty("project.test.coverage.branch.min").get()

        reports {
            total {
                filters {
                    excludes {
                        classes("*Fake*", "*Test")
                    }
                }

                verify {
                    onCheck = true
                    rule("Branch coverage must exceed $minBranchCoverage%") {
                        groupBy = GroupingEntityType.APPLICATION
                        minBound(
                            minValue = minBranchCoverage.toInt(),
                            coverageUnits = CoverageUnit.BRANCH
                        )
                    }
                }
            }
        }
    }

    plugins.withId(libs.plugins.publish.maven.get().pluginId) {
        extensions.configure<PublishingExtension> {
            repositories {
                maven {
                    name = providers.gradleProperty("project.publish.maven.name").get()
                    url = uri(providers.gradleProperty("project.publish.maven.url").get())
                    // Only the machine that publishes holds the credentials.
                    credentials {
                        username = providers.gradleProperty("project.publish.maven.username").orNull
                        password = providers.gradleProperty("project.publish.maven.password").orNull
                    }
                }
            }
        }

        plugins.withId(libs.plugins.android.library.get().pluginId) {
            extensions.configure<LibraryExtension> {
                publishing {
                    singleVariant("release") {
                        withSourcesJar()
                    }
                }
            }
            extensions.configure<PublishingExtension> {
                publications {
                    register<MavenPublication>("release") {
                        // AGP creates the release component only after the project evaluates.
                        afterEvaluate {
                            from(components["release"])
                        }
                    }
                }
            }
        }
    }
}

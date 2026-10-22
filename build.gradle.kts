import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit
import kotlinx.kover.gradle.plugin.dsl.GroupingEntityType
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.dokka)
}

dependencies {
    dokka(project(":app"))
}

allprojects {
    group = providers.gradleProperty("project.group").get()
    version = providers.gradleProperty("project.version").get()
}

subprojects {
    val libs = rootProject.libs

    apply(plugin = libs.plugins.detekt.get().pluginId)
    extensions.configure<DetektExtension> {
        autoCorrect = true
        buildUponDefaultConfig = true
        config.setFrom(rootProject.layout.projectDirectory.file("detekt.yml"))
        source.setFrom(
            "src/main/java",
            "src/test/java",
            "src/main/kotlin",
            "src/test/kotlin",
            "src/commonMain/kotlin",
            "src/commonTest/kotlin",
            "src/androidMain/kotlin",
            "src/androidUnitTest/kotlin",
            "src/androidTest/kotlin",
            "src/iosMain/kotlin",
            "src/iosTest/kotlin"
        )
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

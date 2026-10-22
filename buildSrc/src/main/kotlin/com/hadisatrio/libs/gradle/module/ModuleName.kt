package com.hadisatrio.libs.gradle.module

import org.gradle.api.Project

/**
 * The name of a [Project] in the form `<role>-<target>-<capability>`, for example `lib-kmp-greeting`.
 *
 * The role is `app` or `lib`. The `project.domain` property gives the reverse domain of the owner.
 *
 * @param project The module that carries the name.
 * @throws IllegalArgumentException If the name does not have this form, or if `project.domain` is blank.
 */
class ModuleName(private val project: Project) {

    private val domain = project.providers.gradleProperty("project.domain").getOrElse("")
    private val parts = project.name.split("-")

    init {
        require(domain.isNotBlank()) {
            "The property project.domain must give the reverse domain of the owner; given was \"$domain\"."
        }
        require(parts.size == 3 && parts.none { it.isBlank() }) {
            "A module name must have the form <role>-<target>-<capability>; given was \"${project.name}\"."
        }
        require(role in ROLE_SEGMENTS) {
            "The role of a module must be one of ${ROLE_SEGMENTS.keys}; given was \"$role\" in \"${project.name}\"."
        }
    }

    private val role get() = parts[0]
    private val target get() = parts[1]
    private val capability get() = parts[2]

    /**
     * The Maven group of the module, for example `com.hadisatrio.libs.kmp`.
     */
    val mavenGroup: String get() = "$domain.${ROLE_SEGMENTS.getValue(role)}.$target"

    /**
     * The Maven artifact of the module, for example `greeting`.
     */
    val mavenArtifact: String get() = capability

    /**
     * The root package of the module, for example `com.hadisatrio.libs.kmp.greeting`.
     */
    val packageName: String get() = "$mavenGroup.$capability"

    private companion object {
        val ROLE_SEGMENTS = mapOf("app" to "apps", "lib" to "libs")
    }
}

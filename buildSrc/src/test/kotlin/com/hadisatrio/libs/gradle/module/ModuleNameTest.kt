package com.hadisatrio.libs.gradle.module

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ModuleNameTest {
    @Test
    fun `gives the package of a library`() {
        val moduleName = ModuleName(project(name = "lib-kmp-greeting", domain = "com.hadisatrio"))

        assertEquals("com.hadisatrio.libs.kmp.greeting", moduleName.packageName)
    }

    @Test
    fun `gives the package of an app`() {
        val moduleName = ModuleName(project(name = "app-android-checkout", domain = "org.example"))

        assertEquals("org.example.apps.android.checkout", moduleName.packageName)
    }

    @Test
    fun `gives the Maven group of a module`() {
        val moduleName = ModuleName(project(name = "lib-kmp-greeting", domain = "com.hadisatrio"))

        assertEquals("com.hadisatrio.libs.kmp", moduleName.mavenGroup)
    }

    @Test
    fun `gives the Maven artifact of a module`() {
        val moduleName = ModuleName(project(name = "lib-kmp-greeting", domain = "com.hadisatrio"))

        assertEquals("greeting", moduleName.mavenArtifact)
    }

    @Test
    fun `refuses a name with fewer than three parts`() {
        assertFailsWith<IllegalArgumentException> { ModuleName(project(name = "greeting", domain = "com.hadisatrio")) }
    }

    @Test
    fun `refuses a name with more than three parts`() {
        assertFailsWith<IllegalArgumentException> { ModuleName(project(name = "lib-kmp-greeting-extra", domain = "com.hadisatrio")) }
    }

    @Test
    fun `refuses a name with a blank part`() {
        assertFailsWith<IllegalArgumentException> { ModuleName(project(name = "lib--greeting", domain = "com.hadisatrio")) }
    }

    @Test
    fun `refuses an unknown role`() {
        assertFailsWith<IllegalArgumentException> { ModuleName(project(name = "tool-jvm-greeting", domain = "com.hadisatrio")) }
    }

    @Test
    fun `refuses a project without a domain`() {
        assertFailsWith<IllegalArgumentException> { ModuleName(project(name = "lib-kmp-greeting", domain = "")) }
    }

    private fun project(name: String, domain: String): Project {
        val gradleUserHome = createTempDirectory().toFile()
        gradleUserHome.resolve("gradle.properties").writeText("project.domain=$domain\n")

        return ProjectBuilder.builder()
            .withName(name)
            .withGradleUserHomeDir(gradleUserHome)
            .build()
    }
}

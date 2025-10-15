package com.hadisatrio.libs.gradle.semver

/**
 * Represents a semantic version with components like major, minor, patch, and an optional suffix.
 * Implements [Comparable] to allow versions to be sorted and compared.
 */
interface Semver : Comparable<Semver> {

    val major: Int
    val minor: Int
    val patch: Int
    val suffix: String

    /**
     * Returns the original string representation of the version.
     */
    override fun toString(): String

    /**
     * Converts the semantic version into an integer suitable for Android's `versionCode`.
     * The integer is guaranteed to be monotonically increasing.
     */
    fun toInt(): Int

    /**
     * Compares this version with another, based on their integer representation.
     */
    override fun compareTo(other: Semver): Int = this.toInt().compareTo(other.toInt())
}

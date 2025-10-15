package com.hadisatrio.libs.gradle.semver

/**
 * An implementation of [Semver] that parses a version from a literal string.
 *
 * @param value The semantic version string (e.g., "1.2.3-rc.1").
 */
class LiteralSemver(
    private val value: String
) : Semver {

    private val matchResult: MatchResult

    init {
        val semverRegex = """^(\d+)\.(\d+)\.(\d+)(?:-([a-zA-Z0-9.-]+))?$""".toRegex()
        matchResult = semverRegex.find(value)
            ?: throw IllegalArgumentException("Invalid semver format: '$value'.")
    }

    override val major: Int by lazy { matchResult.destructured.component1().toInt() }
    override val minor: Int by lazy { matchResult.destructured.component2().toInt() }
    override val patch: Int by lazy { matchResult.destructured.component3().toInt() }
    override val suffix: String by lazy { matchResult.destructured.component4() }

    override fun toString(): String = value

    /**
     * Converts the semantic version into an integer with the format `MMNNNPPSS`.
     * - MM: Major (0-99)
     * - NNN: Minor (0-999)
     * - PP: Patch (0-99)
     * - SS: Stage priority (snapshot: 10, alpha: 20, beta: 30, rc: 40, final: 99)
     */
    override fun toInt(): Int {
        if (major > 99) throw IllegalArgumentException("Major version cannot be greater than 99.")
        if (minor > 999) throw IllegalArgumentException("Minor version cannot be greater than 999.")
        if (patch > 99) throw IllegalArgumentException("Patch version cannot be greater than 99.")

        var stageCode = 99 // Default for final releases

        if (suffix.isNotBlank()) {
            val stageRegex = """([a-zA-Z]+)[.-]?(\d*)""".toRegex()
            val stageMatch = stageRegex.find(suffix.lowercase())

            val stageName: String
            val stageVersion: Int

            if (stageMatch != null) {
                val (name, versionStr) = stageMatch.destructured
                stageName = name
                stageVersion = versionStr.toIntOrNull() ?: 0
            } else if (suffix.lowercase() == "snapshot") {
                stageName = "snapshot"
                stageVersion = 0
            } else {
                stageName = "unknown"
                stageVersion = 0
            }

            if (stageName == "snapshot" && stageVersion != 0) {
                throw IllegalArgumentException("Snapshot version number must be 0. Found: $stageVersion.")
            }

            if (stageVersion > 9) {
                throw IllegalArgumentException("Pre-release version number must be 9 or less. Found: $stageVersion.")
            }

            val stagePriority = when (stageName) {
                "snapshot" -> 10
                "alpha" -> 20
                "beta" -> 30
                "rc" -> 40
                else -> 0
            }
            stageCode = stagePriority + stageVersion
        }

        return (major * 10_000_000) + (minor * 10_000) + (patch * 100) + stageCode
    }
}

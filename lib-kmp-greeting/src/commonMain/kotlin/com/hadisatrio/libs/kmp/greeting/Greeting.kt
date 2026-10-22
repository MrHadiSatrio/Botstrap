package com.hadisatrio.libs.kmp.greeting

/**
 * A salutation to one addressee, by name.
 *
 * @param name The name of the addressee.
 * @throws IllegalArgumentException If [name] is blank.
 */
class Greeting(private val name: String) {

    init {
        require(name.isNotBlank()) { "The name of the addressee must not be blank; given was \"$name\"." }
    }

    /**
     * Returns the words of the greeting, for example `Hello Kotlin!`.
     */
    override fun toString(): String = "Hello $name!"
}

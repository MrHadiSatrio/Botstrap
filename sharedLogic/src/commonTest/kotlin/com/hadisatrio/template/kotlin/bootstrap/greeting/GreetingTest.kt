package com.hadisatrio.template.kotlin.bootstrap.greeting

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GreetingTest {
    @Test
    fun `says hello to its addressee by name`() {
        assertEquals("Hello Kotlin!", Greeting(name = "Kotlin").toString())
        assertEquals("Hello Android!", Greeting(name = "Android").toString())
    }

    @Test
    fun `refuses a blank name`() {
        assertFailsWith<IllegalArgumentException> { Greeting(name = " ") }
    }
}

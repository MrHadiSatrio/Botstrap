package com.hadisatrio.template.kotlin.bootstrap

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.hadisatrio.template.kotlin.bootstrap.greeting.Greeting
import com.hadisatrio.template.kotlin.bootstrap.greeting.GreetingScreen

/**
 * Shows a greeting on the whole page.
 */
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        GreetingScreen(greeting = Greeting(name = "Web"))
    }
}

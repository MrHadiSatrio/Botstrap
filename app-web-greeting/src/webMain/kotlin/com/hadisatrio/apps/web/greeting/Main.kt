package com.hadisatrio.apps.web.greeting

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.hadisatrio.libs.cmp.greeting.GreetingScreen
import com.hadisatrio.libs.kmp.greeting.Greeting

/**
 * Shows a greeting on the whole page.
 */
@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        GreetingScreen(greeting = Greeting(name = "Web"))
    }
}

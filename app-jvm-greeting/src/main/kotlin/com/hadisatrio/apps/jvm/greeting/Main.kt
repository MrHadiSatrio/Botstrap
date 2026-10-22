package com.hadisatrio.apps.jvm.greeting

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.hadisatrio.libs.cmp.greeting.GreetingScreen
import com.hadisatrio.libs.kmp.greeting.Greeting

/**
 * Opens a window that shows a greeting.
 */
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Greeting") {
        GreetingScreen(greeting = Greeting(name = "Desktop"))
    }
}

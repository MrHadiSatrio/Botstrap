package com.hadisatrio.template.kotlin.bootstrap

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.hadisatrio.libs.kmp.greeting.Greeting
import com.hadisatrio.template.kotlin.bootstrap.greeting.GreetingScreen

/**
 * Opens a window that shows a greeting.
 */
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Bootstrap") {
        GreetingScreen(greeting = Greeting(name = "Desktop"))
    }
}

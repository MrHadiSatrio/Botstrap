package com.hadisatrio.template.kotlin.bootstrap

import androidx.compose.ui.window.Window
import com.hadisatrio.libs.kmp.greeting.Greeting
import com.hadisatrio.template.kotlin.bootstrap.greeting.GreetingScreen
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

/**
 * Opens a window that shows a greeting.
 */
fun main() {
    NSApplication.sharedApplication()
    Window(title = "Bootstrap") {
        GreetingScreen(greeting = Greeting(name = "macOS"))
    }
    NSApp?.run()
}

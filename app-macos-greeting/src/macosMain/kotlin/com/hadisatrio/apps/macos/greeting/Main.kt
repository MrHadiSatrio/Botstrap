package com.hadisatrio.apps.macos.greeting

import androidx.compose.ui.window.Window
import com.hadisatrio.libs.cmp.greeting.GreetingScreen
import com.hadisatrio.libs.kmp.greeting.Greeting
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

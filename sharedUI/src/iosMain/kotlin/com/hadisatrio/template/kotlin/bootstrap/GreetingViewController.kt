package com.hadisatrio.template.kotlin.bootstrap

import androidx.compose.ui.window.ComposeUIViewController
import com.hadisatrio.libs.kmp.greeting.Greeting
import com.hadisatrio.template.kotlin.bootstrap.greeting.GreetingScreen
import platform.UIKit.UIViewController

/**
 * Returns a [UIViewController] that shows a greeting.
 */
fun greetingViewController(): UIViewController = ComposeUIViewController {
    GreetingScreen(greeting = Greeting(name = "iOS"))
}

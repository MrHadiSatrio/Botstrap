@file:Suppress("FunctionNaming")

package com.hadisatrio.template.kotlin.bootstrap.greeting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hadisatrio.libs.kmp.greeting.Greeting
import com.hadisatrio.template.kotlin.bootstrap.ui.theme.BootstrapTheme

/**
 * Shows [greeting] on a full screen, in [BootstrapTheme].
 */
@Composable
fun GreetingScreen(greeting: Greeting) {
    BootstrapTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            GreetingText(
                greeting = greeting,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

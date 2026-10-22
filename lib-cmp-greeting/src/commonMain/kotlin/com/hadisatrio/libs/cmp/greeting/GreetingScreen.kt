@file:Suppress("FunctionNaming")

package com.hadisatrio.libs.cmp.greeting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hadisatrio.libs.cmp.greeting.theme.GreetingTheme
import com.hadisatrio.libs.kmp.greeting.Greeting

/**
 * Shows [greeting] on a full screen, in [GreetingTheme].
 */
@Composable
fun GreetingScreen(greeting: Greeting) {
    GreetingTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            GreetingText(
                greeting = greeting,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

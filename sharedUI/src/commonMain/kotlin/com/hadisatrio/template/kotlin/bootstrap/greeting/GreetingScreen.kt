@file:Suppress("FunctionNaming")

package com.hadisatrio.template.kotlin.bootstrap.greeting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hadisatrio.template.kotlin.bootstrap.ui.theme.BootstrapTheme

/**
 * Shows a greeting for [name] on a full screen, in [BootstrapTheme].
 */
@Composable
fun GreetingScreen(name: String) {
    BootstrapTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Greeting(
                name = name,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

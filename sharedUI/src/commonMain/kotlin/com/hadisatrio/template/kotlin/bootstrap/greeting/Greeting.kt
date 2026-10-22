@file:Suppress("FunctionNaming")

package com.hadisatrio.template.kotlin.bootstrap.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hadisatrio.template.kotlin.bootstrap.ui.theme.BootstrapTheme

/**
 * Shows a greeting for [name].
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/**
 * Shows [Greeting] in the preview pane of the IDE.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BootstrapTheme {
        Greeting("Android")
    }
}

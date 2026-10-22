@file:Suppress("FunctionNaming")

package com.hadisatrio.template.kotlin.bootstrap.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hadisatrio.template.kotlin.bootstrap.ui.theme.BootstrapTheme

/**
 * Shows [greeting] as text.
 */
@Composable
fun GreetingText(greeting: Greeting, modifier: Modifier = Modifier) {
    Text(
        text = greeting.toString(),
        modifier = modifier
    )
}

/**
 * Shows [GreetingText] in the preview pane of the IDE.
 */
@Preview(showBackground = true)
@Composable
fun GreetingTextPreview() {
    BootstrapTheme {
        GreetingText(Greeting(name = "Android"))
    }
}

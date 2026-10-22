@file:Suppress("FunctionNaming")

package com.hadisatrio.libs.cmp.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hadisatrio.libs.cmp.greeting.theme.GreetingTheme
import com.hadisatrio.libs.kmp.greeting.Greeting

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
    GreetingTheme {
        GreetingText(Greeting(name = "Android"))
    }
}

@file:Suppress("FunctionNaming")

package com.hadisatrio.libs.cmp.greeting.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
     */
)

/**
 * Applies the Material theme of the app to [content].
 *
 * @param darkTheme Whether [content] takes the dark color scheme.
 * @param dynamicColor Whether the colors come from the wallpaper, on Android 12 and later.
 * @param content The UI that takes the theme.
 */
@Composable
fun GreetingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val staticColorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val colorScheme = if (dynamicColor) {
        wallpaperColorScheme(darkTheme = darkTheme, fallback = staticColorScheme)
    } else {
        staticColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

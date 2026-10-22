package com.hadisatrio.template.kotlin.bootstrap.ui.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun wallpaperColorScheme(darkTheme: Boolean, fallback: ColorScheme): ColorScheme {
    val wallpaperColorsAreAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    if (!wallpaperColorsAreAvailable) return fallback

    val context = LocalContext.current
    return if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}

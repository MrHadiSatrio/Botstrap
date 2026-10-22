package com.hadisatrio.libs.cmp.greeting.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal expect fun wallpaperColorScheme(darkTheme: Boolean, fallback: ColorScheme): ColorScheme

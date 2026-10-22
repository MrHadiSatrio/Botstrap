package com.hadisatrio.template.kotlin.bootstrap.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal expect fun wallpaperColorScheme(darkTheme: Boolean, fallback: ColorScheme): ColorScheme

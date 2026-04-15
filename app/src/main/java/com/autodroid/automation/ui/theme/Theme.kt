package com.autodroid.automation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00FF00), // Neon Green
    secondary = Color(0xFF141414),
    tertiary = Color(0xFFE4E3E0),
    background = Color(0xFF050505),
    surface = Color(0xFF151619)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF008000),
    secondary = Color(0xFFE4E3E0),
    tertiary = Color(0xFF141414)
)

@Composable
fun AutoDroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

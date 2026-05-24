package com.mirzaev.booklibraryapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF7E9EB5),
    onPrimary = Color(0xFFE6E6E6),
    primaryContainer = Color(0xFF2F3E4A),
    onPrimaryContainer = Color(0xFFD6E3F0),
    secondary = Color(0xFF9EB09F),
    onSecondary = Color(0xFFE6E6E6),
    secondaryContainer = Color(0xFF2E3A2E),
    onSecondaryContainer = Color(0xFFE2EDE2),
    tertiary = Color(0xFFC4B49B),
    onTertiary = Color(0xFFE6E6E6),
    background = Color(0xFF1E1E1E),
    onBackground = Color(0xFFE0E0E0),
    surface = Color(0xFF2A2A2A),
    onSurface = Color(0xFFE0E0E0),
    error = Color(0xFFB88686),
    onError = Color(0xFF1A1A1A)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF9BB8D3),
    onPrimary = Color(0xFF1A1A1A),
    primaryContainer = Color(0xFFD6E3F0),
    onPrimaryContainer = Color(0xFF1A1A1A),
    secondary = Color(0xFFC5D6C6),
    onSecondary = Color(0xFF1A1A1A),
    secondaryContainer = Color(0xFFE2EDE2),
    onSecondaryContainer = Color(0xFF1A1A1A),
    tertiary = Color(0xFFE5CFB4),
    onTertiary = Color(0xFF1A1A1A),
    background = Color(0xFFFFF9F0),
    onBackground = Color(0xFF2C2C2C),
    surface = Color(0xFFFFF9F0),
    onSurface = Color(0xFF2C2C2C),
    error = Color(0xFFD4A5A5),
    onError = Color(0xFF1A1A1A)
)

@Composable
fun BookLibraryAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean =  false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package com.example.emoto.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = MilkyMatcha,
    onPrimary = Black,
    secondary = PastelPink,
    onSecondary = Black,
    tertiary = TeaGreen,
    onTertiary = White,
    background = Black,
    onBackground = MilkyMatcha,
    surface = Black,
    onSurface = MilkyMatcha
)

private val LightColorScheme = lightColorScheme(
    primary = MilkyMatcha,
    onPrimary = Black,
    secondary = PastelPink,
    onSecondary = Black,
    tertiary = TeaGreen,
    onTertiary = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

@Composable
fun EmotoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

    val shapes = Shapes(
        medium = RoundedCornerShape(10.dp)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = shapes
    )
}
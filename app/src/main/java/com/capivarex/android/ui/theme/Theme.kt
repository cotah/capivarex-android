package com.capivarex.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// CAPIVAREX Colors
val Gold = Color(0xFFD4A017)
val GoldBright = Color(0xFFF5C842)
val GoldDim = Color(0xFF8B6914)
val VoidBlack = Color(0xFF0A0A0A)
val Surface1 = Color(0xFF111111)
val Surface2 = Color(0xFF1A1A1A)
val Surface3 = Color(0xFF222222)
val TextPrimary = Color(0xFFF8F8F8)
val TextMuted = Color(0xFF8B8B8B)
val GlassBorder = Color(0x1AFFFFFF) // white/10
val Error = Color(0xFFEF4444)
val Success = Color(0xFF22C55E)

private val CapivarexColors = darkColorScheme(
    primary = Gold,
    onPrimary = VoidBlack,
    primaryContainer = GoldDim,
    onPrimaryContainer = GoldBright,
    secondary = Surface3,
    onSecondary = TextPrimary,
    background = VoidBlack,
    onBackground = TextPrimary,
    surface = Surface1,
    onSurface = TextPrimary,
    surfaceVariant = Surface2,
    onSurfaceVariant = TextMuted,
    outline = GlassBorder,
    error = Error,
    onError = TextPrimary,
)

private val CapivarexTypography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = TextPrimary,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        color = TextPrimary,
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = TextPrimary,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = TextPrimary,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        color = TextPrimary,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        color = TextMuted,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        color = TextMuted,
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = Gold,
    ),
)

@Composable
fun CapivarexTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CapivarexColors,
        typography = CapivarexTypography,
        content = content,
    )
}

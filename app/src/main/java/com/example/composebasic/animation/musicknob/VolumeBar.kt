package com.example.composebasic.animation.musicknob

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 11,
    barSpacing: Dp = 6.dp,
    baseCornerDp: Dp = 6.dp
) {
    val density = LocalDensity.current

    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val w = constraints.maxWidth.toFloat().coerceAtLeast(1f)
        val h = constraints.maxHeight.toFloat().coerceAtLeast(1f)
        val spacingPx = with(density) { barSpacing.toPx() }
        val baseCornerPx = with(density) { baseCornerDp.toPx() }

        val barWidth = remember(barCount, w) {
            val totalSpacing = spacingPx * (barCount - 1)
            max(4f, (w - totalSpacing) / barCount)
        }

        val cGreen = Color(0xFF8BC34A)
        val cYellow = Color(0xFFFFEB3B)
        val cOrange = Color(0xFFFF9800)
        val cRed = Color(0xFFF44336)
        val inactiveColor = Color(0xFF424242)

        val targetColors = List(barCount) { i ->
            val frac = if (barCount > 1) i.toFloat() / (barCount - 1) else 0f
            when {
                frac <= 0.33f -> lerp(cGreen, cYellow, frac / 0.33f)
                frac <= 0.66f -> lerp(cYellow, cOrange, (frac - 0.33f) / 0.33f)
                else -> lerp(cOrange, cRed, (frac - 0.66f) / (1f - 0.66f))
            }
        }

        val animatedColors = List(barCount) { i ->
            val showActive = i <= activeBars
            animateColorAsState(targetValue = if (showActive) targetColors[i] else inactiveColor, animationSpec = tween(220), label = "").value
        }

        val mid = (barCount - 1) / 2f
        val rawHeights = List(barCount) { i ->
            val dist = abs(i - mid)
            val symmetry = 1f - (dist / mid) // center closer to 1, sides closer to 0
            val variation = 0.45f + 0.55f * symmetry // between 0.45 .. 1.0
            variation
        }

        val animatedHeights = List(barCount) { i ->
            val isActive = i <= activeBars
            val progress = if (isActive) 0.9f else 0.25f
            val targetFactor = rawHeights[i] * progress
            animateFloatAsState(targetValue = targetFactor, animationSpec = tween(220)).value
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            for (i in 0 until barCount) {
                val left = i * (barWidth + spacingPx)
                val factor = animatedHeights[i].coerceIn(0.05f, 1f)
                val barH = h * factor
                val top = (h - barH) / 2f
                val corner = CornerRadius(baseCornerPx, baseCornerPx)
                drawRoundRect(
                    color = animatedColors[i],
                    topLeft = Offset(left, top),
                    size = Size(barWidth, barH),
                    cornerRadius = corner
                )
                val shineH = max(1f, barH * 0.06f)
                drawRoundRect(
                    color = Color.White.copy(alpha = 0.06f),
                    topLeft = Offset(left, top),
                    size = Size(barWidth, shineH),
                    cornerRadius = CornerRadius(baseCornerPx, baseCornerPx)
                )
            }
        }
    }
}

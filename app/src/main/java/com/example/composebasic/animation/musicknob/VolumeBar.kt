package com.example.composebasic.animation.musicknob

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun VolumeMeter(
    modifier: Modifier = Modifier,
    volume: Float,           // 0f .. 1f
    barCount: Int = 12,
    barSpacing: Dp = 6.dp,
    barCornerRadius: Dp = 6.dp,
    barMinHeightFraction: Float = 0.18f // minimum bar height relative to canvas height
) {
    val safeVolume = volume.coerceIn(0f, 1f)

    val animatedVolume by animateFloatAsState(
        targetValue = safeVolume,
        animationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    val targetColor = when {
        animatedVolume <= 0.33f -> Color(0xFFEE5253)            // red-ish
        animatedVolume <= 0.66f -> Color(0xFFF6B93B)            // yellow-ish
        else -> Color(0xFF2ED573)                              // green-ish
    }
    val animatedColor by animateColorAsState(targetValue = targetColor)

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                val canvasWidth = constraints.maxWidth.toFloat()
                val canvasHeight = constraints.maxHeight.toFloat()
                val spacingPx = with(LocalDensity.current) { barSpacing.toPx() }

                val totalSpacing = spacingPx * (barCount - 1)
                val barWidth = (canvasWidth - totalSpacing) / barCount

                Canvas(modifier = Modifier.fillMaxSize()) {
                    for (i in 0 until barCount) {
                        val profileFactor = (i + 1).toFloat() / barCount.toFloat()
                        val heightFraction = barMinHeightFraction + (profileFactor * animatedVolume * (1f - barMinHeightFraction))
                        val barHeight = canvasHeight * heightFraction

                        val left = i * (barWidth + spacingPx)
                        val top = canvasHeight - barHeight

                        val barGradient = Brush.verticalGradient(
                            colors = listOf(animatedColor.copy(alpha = 0.95f), animatedColor.copy(alpha = 0.65f)),
                            startY = top,
                            endY = top + barHeight,
                            tileMode = TileMode.Clamp
                        )

                        drawRoundRect(
                            brush = barGradient,
                            topLeft = Offset(left, top),
                            size = Size(barWidth, barHeight),
                            cornerRadius = CornerRadius(barCornerRadius.toPx(), barCornerRadius.toPx())
                        )

                        val activeThreshold = animatedVolume
                        if (profileFactor <= (activeThreshold + 0.001f)) {
                            val highlightHeight = barHeight * 0.12f
                            drawRoundRect(
                                color = Color.White.copy(alpha = 0.06f),
                                topLeft = Offset(left, top),
                                size = Size(barWidth, highlightHeight),
                                cornerRadius = CornerRadius(barCornerRadius.toPx() * 0.7f, barCornerRadius.toPx() * 0.7f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val percent = (animatedVolume * 100).toInt()
            Text(
                text = "$percent%",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = animatedColor
            )
        }
    }
}

@Composable
fun VolumeMeterDemo(modifier: Modifier = Modifier) {
    var sliderValue by remember { mutableStateOf(0.38f) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VolumeMeter(
            modifier = Modifier.fillMaxWidth(),
            volume = sliderValue,
            barCount = 14,
            barSpacing = 6.dp,
            barCornerRadius = 10.dp,
            barMinHeightFraction = 0.14f
        )

        Spacer(modifier = Modifier.height(18.dp))

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

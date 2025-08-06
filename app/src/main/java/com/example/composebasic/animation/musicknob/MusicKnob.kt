package com.example.composebasic.animation.musicknob

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composebasic.R
import kotlin.math.PI
import kotlin.math.atan2

@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,        // gap at top (degrees)
    sizeDp: Int = 140,              // knob size in dp
    onValueChange: (Float) -> Unit
) {
    // rotation in degrees (we initialize at min angle)
    var rotation by remember { mutableFloatStateOf(limitAngle) }

    // center in window coordinates
    var centerX by remember { mutableFloatStateOf(0f) }
    var centerY by remember { mutableFloatStateOf(0f) }

    Image(
        painter = painterResource(id = R.drawable.knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .size(sizeDp.dp)
            .onGloballyPositioned { layoutCoordinates ->
                val pos = layoutCoordinates.positionInWindow()
                centerX = pos.x + layoutCoordinates.size.width / 2f
                centerY = pos.y + layoutCoordinates.size.height / 2f
            }
            .pointerInteropFilter { event ->
                val rawX = event.rawX
                val rawY = event.rawY

                val angleRad = atan2(rawY - centerY, rawX - centerX)
                var angleDeg = Math.toDegrees(angleRad.toDouble()).toFloat() // -180..180
                if (angleDeg < 0f) angleDeg += 360f
                val angleNormalized = angleDeg // 0..360

                val minAngle = limitAngle
                val maxAngle = 360f - limitAngle

                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        if (angleNormalized in minAngle..maxAngle) {
                            val fixedAngle = angleNormalized
                            rotation = fixedAngle
                            // map angle to 0..1 range
                            val percent = (fixedAngle - minAngle) / (maxAngle - minAngle)
                            onValueChange(percent.coerceIn(0f, 1f))
                            true
                        } else {
                            false
                        }
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}

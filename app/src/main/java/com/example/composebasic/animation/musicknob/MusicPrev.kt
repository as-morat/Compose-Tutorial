package com.example.composebasic.animation.musicknob

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.R
import kotlin.math.roundToInt

@Composable
@Preview(showBackground = true)
fun MusicPrev() {
    Box (
        Modifier.fillMaxSize().background(
            brush = Brush.verticalGradient(listOf(
                colorResource(R.color.blue_calm),
                colorResource(R.color.green_mint),
                colorResource(R.color.cyan_pastel),
                colorResource(R.color.purple_lilac)
            )
            )
        ), Alignment.Center
    ){

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .border(10.dp, brush = Brush.linearGradient(listOf(
                    colorResource(R.color.deep_green),
                    colorResource(R.color.magenta_soft),
                    colorResource(R.color.deep_orange)
                )), RoundedCornerShape(20.dp))
                .padding(20.dp)
        ){
            var volume by remember { mutableFloatStateOf(0f) } // 0..1
            val barCount = 12

            // Knob on the left
            MusicKnob(
                modifier = Modifier.size(100.dp)
            ){
                volume = it
            }

            Spacer(Modifier.width(20.dp))

            // Volume meter on the right
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount
            )
        }

    }
}
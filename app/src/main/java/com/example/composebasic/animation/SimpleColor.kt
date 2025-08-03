package com.example.composebasic.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.composebasic.R
import kotlinx.coroutines.delay

@Composable
fun SimpleColor() {

    var currentSize by remember { mutableStateOf(200.dp) }
    val newSize by animateDpAsState(
        currentSize,
        tween (
            2000,
            300,
            LinearEasing
        )

//        spring(
//            Spring.DampingRatioHighBouncy
//        )

//        keyframes {
//            durationMillis = 5000
//            currentSize at 0 with LinearEasing
//            currentSize *1.5f at 1000 with FastOutLinearInEasing
//            currentSize *5f at 5000
//
//        }
    )

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(
                colorResource(
                    R.color.deep_orange
                )
            ),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(newSize)
                .border(5.dp, Color.Black)
                .background(
//                    colorResource(
//                        R.color.green_mint_dark
//                    )
                    color
                ),
            contentAlignment = Alignment.Center
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        currentSize += 30.dp
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow_gold_soft),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase icon",
                        modifier = Modifier.size(32.dp)
                    )
                }

                Button(
                    onClick = {
                       if (currentSize > 180.dp) currentSize -= 30.dp
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.yellow_gold_soft),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Decrease icon",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composebasic.animation.musicknob.MusicKnob
import com.example.composebasic.animation.musicknob.VolumeMeter
import com.example.composebasic.animation.progressbar.CircularProgressBar
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
//                ImageScreen()
//                FontDesign()
//                 ColorBox()
//                SnackBarText()
//                ConstraintScreen()
//                SimpleColor()
                Box (
                    Modifier.fillMaxSize(), Alignment.Center
                ){
//                    CircularProgressBar(
//                        0.82f,
//                        100,
//                    )
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
                            .padding(24.dp)
                    ){
                        var volume by remember { mutableFloatStateOf(0.2f) } // 0..1

                        // Knob on the left
                        MusicKnob(
                            modifier = Modifier,
                            sizeDp = 140,
                            onValueChange = { percent ->
                                volume = percent
                            }
                        )

                        Spacer(Modifier.width(20.dp))

                        // Volume meter on the right
                        VolumeMeter(
                            modifier = Modifier
                                .width(280.dp)
                                .height(160.dp),
                            volume = volume,
                            barCount = 16,
                            barSpacing = 6.dp,
                            barCornerRadius = 8.dp
                        )
                    }

                }
            }
        }
    }
}


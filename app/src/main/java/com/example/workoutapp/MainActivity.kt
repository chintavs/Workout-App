package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.ui.theme.WorkoutAppTheme


import android.speech.tts.TextToSpeech
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main("Android")
                }
            }
        }
    }
}

@Composable
fun Main(name: String) {
    Column(    modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = "Hello $name!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .width(1000.dp)
            ,
            textAlign = TextAlign.Center

        )
        // variable for simple date format.
        val sdf = SimpleDateFormat("'Today is:\n'dd-MM-yyyy")

        // on below line we are creating a variable for
        // current date and time and calling a simple
        // date format in it.
        val currentDateAndTime = sdf.format(Date())

        Text(text = currentDateAndTime,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(40)),
            textAlign = TextAlign.Center

        )
        Text(text = "Today's Workout:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            textDecoration = TextDecoration.Underline
        )
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .size(600.dp, 100.dp)
                .drawBehind {
                    val borderSize = 4.dp.toPx()
                    val y = size.height - borderSize / 2
                    drawLine(
                        color = Color.DarkGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = borderSize
                    )
                }
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(70)),

            )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button( onClick = {},
                modifier = Modifier.padding(12.dp),

                shape = RoundedCornerShape(70)

            ) { Text(text = "My Workout") }

            Button(
                onClick = {},
                modifier = Modifier.padding(12.dp),
                shape = RoundedCornerShape(70)
            ) { Text(text = "My Groups") }

        }
        Text(text = "My Progress",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
                .width(1000.dp)
            ,
            textAlign = TextAlign.Center

        )
        Text(text = "Goals",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(10.dp)
                .width(1000.dp)
            ,
            textAlign = TextAlign.Center

        )
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .size(400.dp, 100.dp)
                .drawBehind {
                    val borderSize = 4.dp.toPx()
                    val y = size.height - borderSize / 2
                    drawLine(
                        color = Color.DarkGray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = borderSize
                    )
                }
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(70)),

            )

    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkoutAppTheme {
        Main("Android")
    }
}
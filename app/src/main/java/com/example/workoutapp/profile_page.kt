package com.example.workoutapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.ui.theme.WorkoutAppTheme

class ProfilePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WorkoutAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Profile("Android")
                }

            }
        }
    }
}

@Composable
// Profile page
fun Profile(name: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "$name's Profile",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .width(1000.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = "Height",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .width(1000.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = "Weight",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .width(1000.dp),
            textAlign = TextAlign.Center

        )
        Text(
            text = "BMI",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .width(1000.dp),
            textAlign = TextAlign.Center

        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {

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

            Text(text = "Workouts",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(10.dp)
                    .width(1000.dp)
                ,
                textAlign = TextAlign.Center

            )
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .size(200.dp, 250.dp)
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

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .size(200.dp, 250.dp)
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
}
@Preview(name="Light Mode", showBackground=true)
@Preview(uiMode= Configuration.UI_MODE_NIGHT_YES, showBackground = true, name="Dark Mode")
@Composable
fun ProfilePreview() {
    WorkoutAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxWidth()) {
            Profile("Android")
        }
    }
}

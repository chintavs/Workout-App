package com.example.workoutapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.workoutapp.ui.theme.Teal200
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import kotlinx.coroutines.launch

class WorkoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        val workoutWeek = listOf(
                            MyWorkoutDay(
                                day = "Saturday",
                                exercises = listOf("Squats", "Leg Press", "Calf Raises")
                            ),
                            MyWorkoutDay(
                                day = "Monday",
                                exercises = listOf("Bench Press", "Incline Bench Press", "Skull Crushers")
                            ),
                            MyWorkoutDay(
                                day = "Tuesday",
                                exercises = listOf("Deadlifts", "Chin-ups", "Barbell Curls")
                            ),
                            MyWorkoutDay(
                                day = "Wednesday",
                                exercises = listOf("Squats", "Leg Press", "Calf Raises")
                            ),
                            MyWorkoutDay(
                                day = "Thursday",
                                exercises = listOf("Bench Press", "Incline Bench Press", "Skull Crushers")
                            ),
                            MyWorkoutDay(
                                day = "Friday",
                                exercises = listOf("Deadlifts", "Chin-ups", "Barbell Curls")
                            ),
                            MyWorkoutDay(
                                day = "Saturday",
                                exercises = listOf("Squats", "Leg Press", "Calf Raises")
                            )
                        )

                        MyWorkouts(
                            myWorkoutWeek = workoutWeek
                        )
                    }
                }
            }
        }
    }
}

/* Composable function MyWorkouts for displaying a user's workout routine by days of teh week. */

/* The workout routine page is displayed in a multi-row layout using expandable rows that list the user's exercises each day. */

/* The user can add exercises to their routines through the plus button at the bottom. */

@Composable
fun MyWorkouts(
    myWorkoutWeek: List<MyWorkoutDay>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){

            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "My Workout Week",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                for (myWorkoutDay in myWorkoutWeek) {
                    MyWorkoutExpandableItem(myWorkoutDay)
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))

                IconButton(
                    onClick = {
                        val intent = Intent(context, RecordActivity::class.java)
                        startActivity(context, intent, null)
                    })
                {
                    Icon(imageVector = Icons.Filled.AddCircle,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                        tint = Teal200)
                }
            }
        }
    }

/* Composable function MyWorkoutExpandableItem that displays a single workout item in a Column layout. The function takes in
   a MyWorkoutDay data class object as a parameter, which contains the Day and a list of exercises. */

/* The workout's name is displayed using a Text composable function within a Row layout, which also
   contains an Icon that toggles the display of the exercises when clicked. */

/* If the user clicks on a row, the "expanded" variable will be set to true, and the MyExerciseItem
   composable function will be called to display the list of exercises. */


data class MyWorkoutDay(val day: String, val exercises: List<String>)
@Composable
fun MyWorkoutExpandableItem(myWorkoutDay: MyWorkoutDay) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        ) {
            Text(text = myWorkoutDay.day, fontWeight = FontWeight.Bold)
            Icon(
                if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "",
                tint = MaterialTheme.colors.secondary,
            )
        }
        if (expanded) {
            for (exercise in myWorkoutDay.exercises) {
                MyExerciseItem(exercise)
            }
        }
    }
}

/* The MyExerciseItem composable function takes in a String parameter, which represents a single exercise
   in the list of exercises. It displays the exercise using a Text composable function within a Row layout. */


@Composable
fun MyExerciseItem(exercise: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = exercise,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 13.sp
        )
    }
}

@Preview(showBackground = true)
@Composable

fun WorkoutPreview() {
    val workoutWeek = listOf(
        MyWorkoutDay(
            day = "Saturday",
            exercises = listOf("Squats", "Leg Press", "Calf Raises")
        ),
        MyWorkoutDay(
            day = "Monday",
            exercises = listOf("Bench Press", "Incline Bench Press", "Skull Crushers")
        ),
        MyWorkoutDay(
            day = "Tuesday",
            exercises = listOf("Deadlifts", "Chin-ups", "Barbell Curls")
        ),
        MyWorkoutDay(
            day = "Wednesday",
            exercises = listOf("Squats", "Leg Press", "Calf Raises")
        ),
        MyWorkoutDay(
            day = "Thursday",
            exercises = listOf("Bench Press", "Incline Bench Press", "Skull Crushers")
        ),
        MyWorkoutDay(
            day = "Friday",
            exercises = listOf("Deadlifts", "Chin-ups", "Barbell Curls")
        ),
        MyWorkoutDay(
            day = "Saturday",
            exercises = listOf("Squats", "Leg Press", "Calf Raises")
        )
    )
    WorkoutAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {

                MyWorkouts(
                    myWorkoutWeek = workoutWeek
                )
            }
        }
    }
}
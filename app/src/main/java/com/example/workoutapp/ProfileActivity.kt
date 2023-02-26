package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutapp.ui.theme.WorkoutAppTheme

class ProfileActivity : ComponentActivity() {
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
                        val workouts = listOf(
                            Workout(
                                name = "",
                                exercises = listOf("")
                            )
                        )

                        val goals = listOf(
                            Goal(
                                name = "",
                                progress = ""
                            )
                        )

                        UserProfile(
                            username = "Pratik",
                            height = "167cm",
                            weight = "57kg",
                            bmi = "21.8",
                            goals = goals,
                            workouts = workouts
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserProfile(
    username: String,
    height: String,
    weight: String,
    bmi: String,
    goals: List<Goal>,
    workouts: List<Workout>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "$username's Profile",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(text = "Height", fontWeight = FontWeight.Bold)
                Text(text = height)
            }
            Column {
                Text(text = "Weight", fontWeight = FontWeight.Bold)
                Text(text = weight)
            }
            Column {
                Text(text = "BMI", fontWeight = FontWeight.Bold)
                Text(text = bmi)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Goals",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        for (goal in goals) {
            GoalItem(goal)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Workouts",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        for (workout in workouts) {
            WorkoutItem(workout)
        }
    }
}

data class Goal(val name: String, val progress: String)
@Composable
fun GoalItem(goal : Goal) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = goal.name, fontWeight = FontWeight.Bold)
            Text(text = goal.progress)
        }
        LinearProgressIndicator(
            progress = goal.progress.toFloat() / 100f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

data class Workout(val name: String, val exercises: List<String>)
@Composable
fun WorkoutItem(workout: Workout) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded }
        ) {
            Text(text = workout.name, fontWeight = FontWeight.Bold)
            Icon(
                if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
                contentDescription = "",
                tint = MaterialTheme.colors.secondary,
            )
        }
        if (expanded) {
            for (exercise in workout.exercises) {
                ExerciseItem(exercise)
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = exercise)
        // add any other relevant information about the exercise here
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val workouts = listOf(
        Workout(
            name = "Chest and Triceps",
            exercises = listOf("Bench Press", "Incline Bench Press", "Skull Crushers")
        ),
        Workout(
            name = "Back and Biceps",
            exercises = listOf("Deadlifts", "Chin-ups", "Barbell Curls")
        ),
        Workout(
            name = "Leg Day",
            exercises = listOf("Squats", "Leg Press", "Calf Raises")
        )
    )
    val goals = listOf(
        Goal(
            name = "Gain 10kg",
            progress = "30"
        ),
        Goal(
            name = "Build Core Strength",
            progress = "60"
        ),
        Goal(
            name = "Gain 3kg of muscle",
            progress = "99"
        )
    )
    WorkoutAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {

                UserProfile(
                    username = "Pratik",
                    height = "167cm",
                    weight = "57kg",
                    bmi = "21.8",
                    goals = goals,
                    workouts = workouts
                )
            }
        }
    }
}
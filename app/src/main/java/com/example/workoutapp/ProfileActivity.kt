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
import androidx.compose.ui.unit.dp
import com.example.workoutapp.ui.theme.WorkoutAppTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        val workouts = listOf(
                            Workout(
                                name = "Chest and Triceps",
                                exercises = listOf("Bench Press", "Incline Bench Press")
                            ),
                            Workout(
                                name = "Back and Biceps",
                                exercises = listOf("Deadlifts", "Pull-ups")
                            ),
                            Workout(
                                name = "Leg Day",
                                exercises = listOf("Squats", "Lunges")
                            )
                        )
                        UserProfile(
                            username = "Pratik",
                            height = "167cm",
                            weight = "57kg",
                            bmi = "21.8",
                            goal1 = "Gain 10kg",
                            goal1Progress = "30",
                            goal2 = "Build Core Strength",
                            goal2Progress = "60",
                            goal3 = "Gain 3kg of muscle",
                            goal3Progress = "20",
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
    goal1: String,
    goal1Progress: String,
    goal2: String,
    goal2Progress: String,
    goal3: String,
    goal3Progress: String,
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
        GoalItem(goalName = goal1, progress = goal1Progress)
        GoalItem(goalName = goal2, progress = goal2Progress)
        GoalItem(goalName = goal3, progress = goal3Progress)
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

@Composable
fun GoalItem(goalName: String, progress: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = goalName, fontWeight = FontWeight.Bold)
            Text(text = progress)
        }
        LinearProgressIndicator(
            progress = progress.toFloat() / 100f,
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
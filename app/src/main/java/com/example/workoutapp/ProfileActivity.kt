package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.example.workoutapp.MainActivity
import kotlinx.coroutines.launch

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

/* Composable function UserProfile for displaying a user's profile information with goals and workouts in Kotlin. */

/* The function takes in several parameters including username, height, weight, BMI, a list of goals,
   a list of workouts, and a modifier to adjust the layout. */

/* The user's profile information is displayed in a Column layout using various Text and Row composable
   functions, and the user's goals and workouts are displayed using the GoalItem and WorkoutItem composable
   functions, respectively.*/

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
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ){
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(

            scaffoldState = scaffoldState,

            topBar = {

                AppBar {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }

                }

            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                DrawerHeader()
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "Home",
                            title = "Home",
                            contentDescription = "Go to Home Screen",
                            icon = Icons.Default.Home
                        ),
                        MenuItem(
                            id = "Profile",
                            title = "Profile",
                            contentDescription = "Go to Profile Page",
                            icon = Icons.Default.Person
                        ),
                        MenuItem(
                            id = "Settings",
                            title = "Settings",
                            contentDescription = "Go to Settings",
                            icon = Icons.Default.Delete
                        ),

                        MenuItem(
                            id = "Login",
                            title = "Login",
                            contentDescription = "Login",
                            icon = Icons.Default.Lock
                        ),
                    ),
                    onItemClick = {

                    }
                )


            },
            content = {
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
        )


    }
}

/* Composable function GoalItem that displays a single goal item in a Column layout. The function takes in a
   Goal data class object as a parameter, which contains the name of the goal and its progress as a percentage. */

/* The goal's name and progress are displayed using two Text composable functions within a Row layout.
   The progress is also displayed using a LinearProgressIndicator composable function.*/

/* The progress parameter is converted to a Float and divided by 100 to display the progress as a value
   between 0 and 1 for the LinearProgressIndicator. */

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

/* Composable function WorkoutItem that displays a single workout item in a Column layout. The function takes in
   a Workout data class object as a parameter, which contains the name of the workout and a list of exercises. */

/* The workout's name is displayed using a Text composable function within a Row layout, which also
   contains an Icon that toggles the display of the exercises when clicked. */

/* If the user clicks on the row, the "expanded" variable will be set to true, and the ExerciseItem
   composable function will be called to display the list of exercises. */


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

/* The ExerciseItem composable function takes in a String parameter, which represents a single exercise
   in the list of exercises. It displays the exercise using a Text composable function within a Row layout. */


@Composable
fun ExerciseItem(exercise: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = exercise)
    }
}


/*     This is a preview of the Workout App UI, displaying a sample user profile with their goals and workouts.
   The preview uses the WorkoutAppTheme to apply the app's styling and displays a UserProfile composable,
   which takes in the user's information and a list of goals and workouts. The sample data includes three
   workouts and three goals, each with their name and progress. The preview showcases how the UserProfile
   composable arranges the user's information and goals and workouts in a visually appealing manner. */

@Preview(showBackground = true)
@Composable
fun DefaultProfilePreview() {
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
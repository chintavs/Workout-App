package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                )
                {
                    /* Creates a a drop down list containing all the days of the week
                    * Each day contains a list of exercises done */

                    MyWorkout(
                        sections = listOf(
                            CollapsableSection(
                                title = "Monday",
                                rows = listOf("Pushups", "Bench Press", " Cardio")
                            ),
                            CollapsableSection(
                                title = "Tuesday",
                                rows = listOf("Pushups", "Bench Press", " Cardio")
                            ),
                            CollapsableSection(
                                title = "Wednesday",
                                rows = listOf("Pushups", "Bench Press", " Cardio")
                            ),
                            CollapsableSection(
                                title = "Thursday",
                                rows = listOf("Pushups", "Bench Press", " Cardio")
                            ),
                            CollapsableSection(
                                title = "Friday",
                                rows = listOf("Pushups", "Bench Press", " Cardio")
                            ),
                        )
                    )
                }
            }
        }
    }
}

data class CollapsableSection(val title: String, val rows: List<String>)

const val MaterialIconDimension = 24f

@Composable
fun MyWorkout(
    sections: List<CollapsableSection>
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }

    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Workouts",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }

    LazyColumn(
        modifier = Modifier
            .padding(75.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = true
    ) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            collapsedState[i] = !collapsed
                        }
                ) {
                    Text(
                        dataItem.title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .weight(1f)
                    )
                }
                Divider()
            }
            if (!collapsed) {
                items(dataItem.rows) { row ->
                    Row {
                        Spacer(modifier = Modifier.size(MaterialIconDimension.dp))
                        Text(
                            row,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        )
                    }
                    Divider()
                }
            }
        }
    }
    Column {
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
                            icon = Icons.Default.Settings
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
            }
        )

    }


}


@Preview(showBackground = true)
@Composable
fun WorkoutPreview() {
    WorkoutAppTheme {
        MyWorkout(
            sections = listOf(
                CollapsableSection(
                    title = "Monday",
                    rows = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                ),
                CollapsableSection(
                    title = "Tuesday",
                    rows = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                ),
                CollapsableSection(
                    title = "Wednesday",
                    rows = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                ),
                CollapsableSection(
                    title = "Thursday",
                    rows = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                ),
                CollapsableSection(
                    title = "Friday",
                    rows = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                ),
            )
        )
    }
}
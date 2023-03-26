package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutapp.ui.theme.WorkoutAppTheme

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
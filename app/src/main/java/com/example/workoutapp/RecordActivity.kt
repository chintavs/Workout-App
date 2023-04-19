package com.example.workoutapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workoutapp.ui.theme.WorkoutAppTheme

class RecordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RecordWorkout()
                }
            }
        }
    }
}

@Composable
fun RecordWorkout() {
    var name by remember { mutableStateOf("") }
    var sets by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Record Workout",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = name,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {name =  it},
            label = {Text (stringResource(R.string.workoutName))})
        OutlinedTextField(
            value = reps,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {reps =  it},
            label = {Text (stringResource(R.string.Reps))})
        OutlinedTextField(
            value = sets,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {sets =  it},
            label = {Text (stringResource(R.string.Sets))})
        OutlinedTextField(
            value = day,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {day =  it},
            label = {Text (stringResource(R.string.Day))})

        Row() {
            IconButton(
                onClick = {
                    Toast.makeText(context, "$name $sets $reps $day", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.padding(horizontal = 20.dp))
            {
                Icon(imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp))
            }

            IconButton(
                onClick = {
                    Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.padding(horizontal = 20.dp))
            {
                Icon(imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecordPreview() {
    WorkoutAppTheme {
        RecordWorkout()
    }
}
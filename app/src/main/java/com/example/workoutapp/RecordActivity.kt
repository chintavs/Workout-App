package com.example.workoutapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
                    RecordWO()
                }
            }
        }
    }
}

@Composable
fun RecordWO() {
    var Name by remember { mutableStateOf("") }
    var Sets by remember { mutableStateOf("") }
    var Reps by remember { mutableStateOf("") }
    var Time by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Record Workout",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = Name,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {Name =  it},
            label = {Text (stringResource(R.string.workoutName))})
        OutlinedTextField(
            value = Reps,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {Reps =  it},
            label = {Text (stringResource(R.string.Reps))})
        OutlinedTextField(
            value = Sets,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {Sets =  it},
            label = {Text (stringResource(R.string.Sets))})
        OutlinedTextField(
            value = Time,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = {Time =  it},
            label = {Text (stringResource(R.string.Time))})

        Button(
            onClick = {
                Toast.makeText(context, "$Name $Sets $Reps $Time", Toast.LENGTH_LONG).show()
            })
        {
            Text(text = "Save")
        }

        Button(
            onClick = {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
            })
        {
            Text(text = "Cancel")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecordPreview() {
    WorkoutAppTheme {
        RecordWO()
    }
}
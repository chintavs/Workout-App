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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.workoutapp.dto.WorkoutRec
import com.example.workoutapp.ui.theme.WorkoutAppTheme

private var selectedWorkout: WorkoutRec = WorkoutRec()
private var inputName: String = ""

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
                    val workoutList: List<WorkoutRec> = listOf(
                        WorkoutRec("Bicep"),
                        WorkoutRec("Triceps"),
                        WorkoutRec("Curl"),
                        WorkoutRec("Squats"),
                        WorkoutRec("Calf Raises")
                    )

                    RecordWorkout(workoutList, selectedWorkout)
                }
            }
        }
    }
}

@Composable
fun RecordWorkout(
    workouts: List<WorkoutRec>,
    selectedWorkout: WorkoutRec = WorkoutRec("")
) {
    var name by remember(selectedWorkout.workoutName) { mutableStateOf(selectedWorkout.workoutName) }
    var sets by remember { mutableStateOf("") }
    var reps by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.Record_Title),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextFieldWithDropdownUsage(
            dataIn = workouts,
            label = stringResource(R.string.Name),
            selectedWorkout = selectedWorkout
        )
        OutlinedTextField(
            value = reps,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = { reps = it },
            label = { Text(stringResource(R.string.Reps)) })
        OutlinedTextField(
            value = sets,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = { sets = it },
            label = { Text(stringResource(R.string.Sets)) })
        OutlinedTextField(
            value = day,
            modifier = Modifier
                .padding(10.dp),
            onValueChange = { day = it },
            label = { Text(stringResource(R.string.Day)) })

        Row(modifier = Modifier.padding(vertical = 60.dp)) {
            IconButton(
                onClick = {
                    Toast.makeText(context, "Workout Saved", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }

            IconButton(
                onClick = {
                    Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                )
            }
        }
    }
}

@Composable
fun TextFieldWithDropdown(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    dropDownExpanded: Boolean,
    list: List<WorkoutRec>,
    label: String = ""
) {
    Box(modifier) {
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused)
                        onDismissRequest()
                },
            value = value,
            onValueChange = setValue,
            label = { Text(label) },
        )
        DropdownMenu(
            expanded = dropDownExpanded,
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = onDismissRequest
        ) {
            list.forEach { text ->
                DropdownMenuItem(onClick = {
                    setValue(
                        TextFieldValue(
                            text.toString(),
                            TextRange(text.toString().length)
                        )
                    )
                    selectedWorkout = text
                }) {
                    Text(text = text.toString())
                }
            }
        }
    }
}


@Composable
fun TextFieldWithDropdownUsage(
    dataIn: List<WorkoutRec>,
    label: String = "",
    selectedWorkout: WorkoutRec = WorkoutRec()
) {

    val dropDownOptions = remember { mutableStateOf(listOf<WorkoutRec>()) }
    val textFieldValue =
        remember(selectedWorkout.workoutName) { mutableStateOf(TextFieldValue(selectedWorkout.workoutName)) }
    val dropDownExpanded = remember { mutableStateOf(false) }

    fun onDropdownDismissRequest() {
        dropDownExpanded.value = false
    }

    fun onValueChanged(value: TextFieldValue) {
        inputName = value.text
        dropDownExpanded.value = true
        textFieldValue.value = value
        dropDownOptions.value = dataIn.filter {
            it.toString().startsWith(value.text) && it.toString() != value.text
        }.take(3)
    }

    TextFieldWithDropdown(
        modifier = Modifier.width(300.dp),
        value = textFieldValue.value,
        setValue = ::onValueChanged,
        onDismissRequest = ::onDropdownDismissRequest,
        dropDownExpanded = dropDownExpanded.value,
        list = dropDownOptions.value,
        label = label
    )
}

@Preview(showBackground = true)
@Composable
fun RecordPreview() {
    WorkoutAppTheme {
        val WorkoutList: List<WorkoutRec> = listOf(
            WorkoutRec("Bicep"),
            WorkoutRec("Tricep"),
            WorkoutRec("Curl")
        )

        RecordWorkout(WorkoutList)
    }
}
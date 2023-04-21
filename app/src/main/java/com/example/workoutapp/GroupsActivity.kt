package com.example.workoutapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutapp.dto.Group
import com.example.workoutapp.dto.User
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList


class GroupsActivity : ComponentActivity() {

    private var selectedGroup: Group? = null
    private val viewModel: MainViewModel by viewModel<MainViewModel>()
    private var groupMembersText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // TODO:  Convert below mock data into parsed data
            // Add mock groups for GroupSpinner function
            val groups = ArrayList<Group>()
            groups.add(
                Group(
                    groupName = "Cool Group",
                    groupMembers = arrayOf(
                        User(
                            displayName = "daltonco",
                            firstName = "Colton",
                            lastName = "Dalton",
                            calorieGoal = "10",
                            workoutsCompleted = "10",
                            workoutDate = "",
                            height = "10",
                            weight = "10",
                            bmi = "10",
                            friendList = arrayOf("")
                        ),
                        User(
                            displayName = "arthursr01",
                            firstName = "Sean",
                            lastName = "Arthur",
                            calorieGoal = "10",
                            workoutsCompleted = "10",
                            workoutDate = "",
                            height = "10",
                            weight = "10",
                            bmi = "10",
                            friendList = arrayOf("")
                        )
                    )
                )
            )
            groups.add(
                Group(
                    groupName = "Dev Group",
                    groupMembers = arrayOf(
                        User(
                            displayName = "kooncewc",
                            firstName = "William",
                            lastName = "Koonce",
                            calorieGoal = "10",
                            workoutsCompleted = "10",
                            workoutDate = "",
                            height = "10",
                            weight = "10",
                            bmi = "10",
                            friendList = arrayOf("")
                        ),
                        User(
                            displayName = "chintavs",
                            firstName = "Vishvak",
                            lastName = "Chintalapalli",
                            calorieGoal = "10",
                            workoutsCompleted = "10",
                            workoutDate = "",
                            height = "10",
                            weight = "10",
                            bmi = "10",
                            friendList = arrayOf("")
                    )
                )
                )
            )
            // End mock groups
            WorkoutAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GroupPage("Android", groups)
                }
            }
        }
    }

    @Composable
    fun GroupPage(name: String, groups: List<Group> = ArrayList<Group>()) {
        val context = LocalContext.current
        val loadingGroup = stringResource(R.string.loading_group)

        Column() {
            Text(
                text = "$name's Groups"
            )
            GroupSpinner(groups = groups)
        }

    }

    @Composable
    fun GroupSpinner(groups: List<Group>) {
        var groupText by remember { mutableStateOf("Group Collection") }
        var expanded by remember { mutableStateOf(false) }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(Modifier
                .padding(24.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = groupText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    groups.forEach { singleGroup ->
                        DropdownMenuItem(onClick = {
                            expanded = false
                            groupText = singleGroup.nameToString()
                            selectedGroup = singleGroup
                            groupMembersText = selectedGroup!!.membersToString()
                        }) {
                            Text(text = singleGroup.nameToString())
                        }
                    }
                }
            }
        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ){
                Row(
                    Modifier
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Group Members:"
                    )
                }

                Row(
                    Modifier
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = groupMembersText
                    )
                }
            }

        }
    }
}
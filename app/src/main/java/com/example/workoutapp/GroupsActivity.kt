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
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import java.util.ArrayList


class GroupsActivity : ComponentActivity() {

    private var selectedGroup: Group? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // TODO:  Convert below mock data into parsed data
            // Add mock groups for GroupSpinner function
            val groups = ArrayList<Group>()
            groups.add(
                Group(
                    groupName = "Cool Group",
                    groupMembers = arrayOf()
                )
            )
            groups.add(
                Group(
                    groupName = "Dev Group",
                    groupMembers = arrayOf()
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

            selectedGroup?.let {
                Text(
                    text = it.membersToString()
                )
            }
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
                        }) {
                            Text(text = singleGroup.toString())
                        }
                    }
                }
            }
        }
    }
}
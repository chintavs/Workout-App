package com.example.workoutapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.workoutapp.ui.theme.WorkoutAppTheme

class GroupsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GroupGreeting("Android")
                }
            }
        }
    }
}
@Composable
fun GroupGreeting(name: String) {
    val context = LocalContext.current
    val loadingGroup = "Loading Group..."
    Column {
        Text(
            text = "$name's Groups"
        )
        Button(
            onClick = {
                Toast.makeText(context, "$loadingGroup", Toast.LENGTH_LONG).show()
            },
            content = {
                Text(text = "Group 1")
            }
        )
        Button(
            onClick = {
                Toast.makeText(context, "$loadingGroup", Toast.LENGTH_LONG).show()
            },
            content = {
                Text(text = "Group 2")
            }
        )
    }

}
@Preview(name="Light Mode", showBackground=true)
@Preview(uiMode= Configuration.UI_MODE_NIGHT_YES, showBackground = true, name="Dark Mode")
@Composable
fun GroupsActivityPreview() {
    WorkoutAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            GroupGreeting("Android")
        }
    }
}



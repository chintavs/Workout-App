package com.example.workoutapp


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.workoutapp.dto.User
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*




class MainActivity : ComponentActivity() {


    private var selectedUser: User? = null
    val MaterialIconDimension = 24f
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            firebaseUser?.let {
                    val user = User(it.uid, "","", "", "", "",
                        arrayOf(""), "", "", "", "")
                    viewModel.user = user
                    viewModel.listenToUserWorkout()
            }
            // TODO:  Convert below mock data into parsed data
            // Add mock users for GroupSpinner function
            val users = ArrayList<User>()
            users.add(User(
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
            ))
            users.add(User(
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
            ))
            // End mock users
            WorkoutAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage("Android")
                    GroupPage(name = "Android", users)
                }

            }
        }
    }

    private fun <E> MutableList<E>.add(element: MutableLiveData<List<E>>) {

    }
    private fun getCurrentDateAndTime(): String {
        val sdf = SimpleDateFormat("'Today is:\n'dd-MM-yyyy")
        return sdf.format(Date())
    }
    @Composable
    fun MainPage(name: String?) {

//Navigation is setup with a Scaffold
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
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
                    // Items within the open drawer
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
                            if (it.title == "Login") {
                                signIn()
                            }

                            println("Clicked on ${it.title}")
                        }
                    )
                },
                content = {
                    Column() {
                        Text(
                            text = stringResource(id = R.string.Hello),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(10.dp)
                                .width(1000.dp),
                            textAlign = TextAlign.Center

                        )
                    }

                    Column() {

                        val currentDateAndTime = getCurrentDateAndTime()

                        Text(
                            text = currentDateAndTime,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(60.dp)
                                .fillMaxWidth()
                                .border(1.dp, Color.DarkGray, shape = RoundedCornerShape(40)),
                            textAlign = TextAlign.Center

                        )
                        Column() {
                            Text(
                                text = stringResource(id = R.string.TodaysWorkout),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(3.dp),
                                textDecoration = TextDecoration.Underline
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary)
                                .size(600.dp, 100.dp)
                                .drawBehind {
                                    val borderSize = 4.dp.toPx()
                                    val y = size.height - borderSize / 2
                                    drawLine(
                                        color = Color.DarkGray,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = borderSize
                                    )
                                }
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(70)),


                            )


                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {},
                                modifier = Modifier.padding(12.dp),

                                shape = RoundedCornerShape(70)

                            ) { Text(text = stringResource(id = R.string.MyWorkout)) }

                            Button(
                                onClick = {},
                                modifier = Modifier.padding(12.dp),
                                shape = RoundedCornerShape(70)
                            ) { Text(text = stringResource(id = R.string.MyGroups)) }

                        }
                        Text(
                            text = stringResource(id = R.string.MyProgress),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(10.dp)
                                .width(1000.dp),
                            textAlign = TextAlign.Center

                        )
                        Text(
                            text = stringResource(id = R.string.Goals),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .padding(10.dp)
                                .width(1000.dp),
                            textAlign = TextAlign.Center

                        )
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary)
                                .size(400.dp, 100.dp)
                                .drawBehind {
                                    val borderSize = 4.dp.toPx()
                                    val y = size.height - borderSize / 2
                                    drawLine(
                                        color = Color.DarkGray,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = borderSize
                                    )
                                }
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(70)),

                            )

                    }


                },
            )
        }

    }

    @Composable
    fun UserSpinner (users: List<User>){
        var userText by remember{ mutableStateOf("User Collection")}
        var expanded by remember { mutableStateOf(false)}
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Row(Modifier
                .padding(24.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = userText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                    users.forEach {
                        singleUser -> DropdownMenuItem(onClick = {
                            expanded = false
                        userText = singleUser.nameToString()
                        selectedUser = singleUser
                    }){
                            Text(text = singleUser.toString())
                    }
                    }
                }
            }
        }
    }

    @Composable
    fun GroupPage(name: String, users: List<User> = ArrayList<User>()) {
        val context = LocalContext.current
        val loadingGroup = stringResource(R.string.loading_group)
        
        Column() {
            Text(
                text = "$name's Groups"
            )
            UserSpinner(users = users)

            // TODO: Create buttons based on groups the user is in
            //Sample Button 1
            Button(
                onClick = {
                    Toast.makeText(context, "$loadingGroup", Toast.LENGTH_LONG).show()

                },
                content = {
                    Text(text = "Group 1")
                }
            )
            //Sample Button 2
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
    @Composable
    fun ProfilePage(name: String) {
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
    @Composable
    fun RecordWorkout() {
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
            androidx.compose.material3.OutlinedTextField(
                value = Name,
                modifier = Modifier
                    .padding(10.dp),
                onValueChange = { Name = it },
                label = { Text(stringResource(R.string.workoutName)) })
            androidx.compose.material3.OutlinedTextField(
                value = Reps,
                modifier = Modifier
                    .padding(10.dp),
                onValueChange = { Reps = it },
                label = { Text(stringResource(R.string.Reps)) })
            androidx.compose.material3.OutlinedTextField(
                value = Sets,
                modifier = Modifier
                    .padding(10.dp),
                onValueChange = { Sets = it },
                label = { Text(stringResource(R.string.Sets)) })
            androidx.compose.material3.OutlinedTextField(
                value = Time,
                modifier = Modifier
                    .padding(10.dp),
                onValueChange = { Time = it },
                label = { Text(stringResource(R.string.Time)) })

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

    data class CollapsableSection(val title: String, val rows: List<String>)

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

    private fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val signinIntent = AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        signinLauncher.launch(signinIntent)
    }

    private val signinLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.signInResult(res)
    }


    private fun signInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            firebaseUser = FirebaseAuth.getInstance().currentUser
            firebaseUser?.let {
                val user = User(it.uid, "","", "", "", "",
                    arrayOf(""), "", "", "", "")
                viewModel.user = user
                viewModel.saveUser()
                viewModel.listenToUserWorkout()
            }
        } else {
            Log.e("MainActivity.kt", "Error logging in " + response?.error?.errorCode)
        }

    }


    @Preview(showBackground = true)
    @Composable
    fun MainPage() {
        WorkoutAppTheme {
            MainPage("Android")
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

}

package com.example.workoutapp


import android.content.Intent
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
import com.example.workoutapp.data.Workout
import com.example.workoutapp.dto.User
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import com.example.workoutapp.dto.Group
import com.example.workoutapp.dto.WorkoutRec
import com.example.workoutapp.ui.theme.Teal200


class MainActivity : ComponentActivity() {
    var selectedWorkout: WorkoutRec = WorkoutRec()
    var inputName: String = ""
    private var selectedUser: User? = null

    private var selectedGroup: Group? = null
    val MaterialIconDimension = 24f
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            firebaseUser?.let {
                val user = User(
                    it.uid, "", "", "", "", "",
                    arrayOf(""), "", "", "", ""
                )
                viewModel.user = user
                viewModel.listenToUserWorkout()
            }
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
                MainPage(arrayListOf(),"", arrayListOf())

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
    fun MainPage(
        goals: List<Goal>,
        calorieGoal: String,
        workouts: List<Workout>
    ) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
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
                DrawerHeader("Menu")
                // Items within the open drawer
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "Profile",
                            title = "Profile",
                            contentDescription = "Go to Profile Page",
                            icon = Icons.Default.Person
                        ),
                        MenuItem(
                            id = "My Workouts",
                            title = "Workouts",
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
                        }  else if (it.title == "Workouts"){
                            val navigate = Intent(this@MainActivity, WorkoutActivity::class.java)
                            startActivity(navigate)
                        } else if (it.title == "Profile"){
                            val navigate = Intent(this@MainActivity, ProfileActivity::class.java)
                            startActivity(navigate)
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


                        ) {
                        Text(
                            text = "$workouts",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center)

                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                val navigate = Intent(this@MainActivity, WorkoutActivity::class.java)
                                startActivity(navigate)
                            },
                            modifier = Modifier.padding(12.dp),

                            shape = RoundedCornerShape(70)

                        ) { Text(text = stringResource(id = R.string.MyWorkout)) }
                        Button(
                            onClick = {
                                val navigate = Intent(this@MainActivity, RecordActivity::class.java)
                                startActivity(navigate)
                            },
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
                        modifier = Modifier.padding(3.dp),
                        textDecoration = TextDecoration.Underline

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

                        ){
                        Text(
                            text = "$goals",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center),
                            textAlign = TextAlign.Left
                        )
                        Text(
                            text = "$calorieGoal",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }

                }
            },
        )


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

    data class MyWorkoutDay(val day: String, val exercises: List<String>)

    @Composable
    fun MyWorkouts(
        myWorkoutWeek: List<MyWorkoutDay>,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ){
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

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
                    DrawerHeader("")
                    DrawerBody(
                        items = listOf(
                            MenuItem(
                                id = "Home",
                                title = "Home",
                                contentDescription = "Go to Home com.example.workoutapp.Screen",
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
                    Column(
                        modifier = modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "My Workout Week",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        for (myWorkoutDay in myWorkoutWeek) {
                            MyWorkoutExpandableItem(myWorkoutDay)
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        IconButton(
                            onClick = {
                                val intent = Intent(context, RecordActivity::class.java)
                                startActivity(intent)
                            })
                        {
                            Icon(imageVector = Icons.Filled.AddCircle,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp),
                                tint = Teal200)
                        }
                    }
                }
            )


        }
    }

    @Composable
    fun MyWorkoutExpandableItem(myWorkoutDay: MyWorkoutDay) {
        var expanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            ) {
                Text(text = myWorkoutDay.day, fontWeight = FontWeight.Bold)
                Icon(
                    if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary,
                )
            }
            if (expanded) {
                for (exercise in myWorkoutDay.exercises) {
                    MyExerciseItem(exercise)
                }
            }
        }
    }

    @Composable
    fun MyExerciseItem(exercise: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = exercise,
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 13.sp
            )
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
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
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
            androidx.compose.material3.OutlinedTextField(
                value = reps,
                modifier = Modifier
                    .padding(10.dp),
                onValueChange = { reps = it },
                label = { Text(stringResource(R.string.Reps)) })
            androidx.compose.material3.OutlinedTextField(
                value = sets,
                modifier = Modifier
                    .padding(10.dp),
                onValueChange = { sets = it },
                label = { Text(stringResource(R.string.Sets)) })
            androidx.compose.material3.OutlinedTextField(
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
            androidx.compose.material3.OutlinedTextField(
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
                val user = User(
                    it.uid, "", "", "", "", "",
                    arrayOf(""), "", "", "", ""
                )
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
    fun MainPagePreview() {
        WorkoutAppTheme {
            MainPage()
            MyWorkouts(
                myWorkoutWeek = listOf(
                    MyWorkoutDay(
                        day = "Monday",
                        exercises = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                    ),
                    MyWorkoutDay(
                        day = "Tuesday",
                        exercises = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                    ),
                    MyWorkoutDay(
                        day = "Wednesday",
                        exercises = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                    ),
                    MyWorkoutDay(
                        day = "Thursday",
                        exercises = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                    ),
                    MyWorkoutDay(
                        day = "Friday",
                        exercises = listOf("Pushups", "Bench Press", " Cardio", "Add Workout")
                    ),
                )
            )
        }

    }

}


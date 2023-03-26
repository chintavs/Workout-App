package com.example.workoutapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.workoutapp.dto.User
import com.example.workoutapp.dto.WorkoutRec
import com.example.workoutapp.ui.theme.WorkoutAppTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*



var user: FirebaseUser? = null

class MainActivity : ComponentActivity() {


    var userWorkout: MutableLiveData<List<WorkoutRec>> = MutableLiveData<List<WorkoutRec>>()

    private lateinit var firestore: FirebaseFirestore
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    var userWork : User? = null

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listentouserWorkout()
    }

    private fun listentouserWorkout() {
        user?.let {
            user ->
            firestore.collection("users").document(user.uid).collection("userWorkout").addSnapshotListener {
                // error handling
                    snapshot, e ->
                if (e != null) {
                    Log.w("Listen Failed", e)
                    return@addSnapshotListener
                }
                snapshot?.let {
                    val alluserWorkout = ArrayList<WorkoutRec>()
                    val documents = snapshot.documents
                    documents.forEach {
                        val userWorkouts = it.toObject(userWorkout::class.java)
                        userWorkouts?.let {
                            alluserWorkout.add(it!!)
                        }

                    }
                    userWorkout.value = alluserWorkout
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                firebaseUser?.let {
                    val user = User(it.uid, "","", "", "", "",
                        arrayOf(""), "", "", "", "")
                    userWork = user
                    listentouserWorkout()
                }
            WorkoutAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main("Android")
                }

            }
        }
    }


    private fun <E> MutableList<E>.add(element: MutableLiveData<List<E>>) {

    }


    @Composable
    fun Main(name: String?) {

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
                            text = "Hello !",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(10.dp)
                                .width(1000.dp),
                            textAlign = TextAlign.Center

                        )
                    }

                    Column() {
                        // variable for simple date format.
                        val sdf = SimpleDateFormat("'Today is:\n'dd-MM-yyyy")

                        // on below line we are creating a variable for
                        // current date and time and calling a simple
                        // date format in it.
                        val currentDateAndTime = sdf.format(Date())

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
                                text = "Today's Workout:",
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

                            ) { Text(text = "My Workout") }

                            Button(
                                onClick = {},
                                modifier = Modifier.padding(12.dp),
                                shape = RoundedCornerShape(70)
                            ) { Text(text = "My Groups") }

                        }
                        Text(
                            text = "My Progress",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(10.dp)
                                .width(1000.dp),
                            textAlign = TextAlign.Center

                        )
                        Text(
                            text = "Goals",
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

    fun signIn() {
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
                userWork = user
                saveUser()
                listentouserWorkout()
            }
        } else {
            Log.e("MainActivitytwo.kt", "Error logging in " + response?.error?.errorCode)
        }

    }
// in any place that saves something that relates to a user you need to use user?.let {}
    private fun saveUser () {
        user?.let {
                user ->
            val handle = firestore.collection("users").document(user.uid).set(user)
            handle.addOnSuccessListener { Log.d("Firebase", "Document Saved") }
            handle.addOnFailureListener { Log.e("Firebase", "Save failed $it ") }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultMainPreview() {
        WorkoutAppTheme {
            Main("Android")
        }

    }
}

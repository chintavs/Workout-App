package com.example.workoutapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workoutapp.dto.User
import com.example.workoutapp.dto.WorkoutRec
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

var user: FirebaseUser? = null

class MainViewModel() : ViewModel() {

    var user: User? = null
    private var userWorkout: MutableLiveData<List<WorkoutRec>> = MutableLiveData<List<WorkoutRec>>()

    private lateinit var firestore: FirebaseFirestore
    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private var userWork : User? = null
    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToUserWorkout()
    }




     fun listenToUserWorkout() {
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
    // in any place that saves something that relates to a user you need to use user?.let {}
     fun saveUser () {
        user?.let {
                user ->
            val handle = firestore.collection("users").document(user.uid).set(user)
            handle.addOnSuccessListener { Log.d("Firebase", "Document Saved") }
            handle.addOnFailureListener { Log.e("Firebase", "Save failed $it ") }
        }
    }

}

private fun <E> ArrayList<E>.add(element: MutableLiveData<List<E>>) {

}

package com.example.workoutapp.service

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.example.workoutapp.RetrofitClientInstance
import com.example.workoutapp.dao.IExerciseDAO
import com.example.workoutapp.dto.WorkoutRec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


class WorkoutService {


    fun fetchWorkouts() {
        val arr = arrayOf("3_4_Sit-Up", "90_90_Hamstring", "Ab_Crunch_Machine", "Ab_Roller", "Adductor",
            "Adductor_Groin", "Advanced_Kettlebell_Windmill", "Air_Bike", "All_Fours_Quad_Stretch", "Alternate_Hammer_Curl",
        "Alternate_Heel_Touchers", "Alternate_Incline_Dumbbell_Curl", "Alternate_Leg_Diagonal_Bound", "Alternating_Cable_Shoulder_Press",
        "Alternating_Deltoid_Raise", "Alternating_Floor_Press", "Alternating_Hang_Clean", "Alternating_Kettlebell_Press",
            "Alternating_Kettlebell_Row", "Alternating_Renegade_Row","Ankle_Circles,Ankle_On_The_Knee" ,"Anterior_Tibialis-SMR","Anti-Gravity_Press"
            ,"Arm_Circles", "Arnold_Dumbbell_Press", "Around_The_Worlds", "Atlas_Stone_Trainer",
           "Atlas_Stones,Axle_Deadlift")
        arr.forEach {
            suspend fun nested() : List<WorkoutRec>? {
                return withContext(Dispatchers.IO) {
                    val service = RetrofitClientInstance.retrofitInstance?.create(IExerciseDAO::class.java)
                    val plants = async { service?.getAllExercises(it) }
                    return@withContext plants.await()?.awaitResponse()?.body()
                }
        }
    }


    }
}
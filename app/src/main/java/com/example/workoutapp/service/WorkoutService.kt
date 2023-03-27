package com.example.workoutapp.service

import com.example.workoutapp.RetrofitClientInstance
import com.example.workoutapp.dao.IExerciseDAO
import com.example.workoutapp.dto.WorkoutRec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


class WorkoutService {

    fun fetchWorkouts(args: Array<String>) {
        val arr = arrayOf("3_4_Sit-Up", "90_90_Hamstring", "Ab_Crunch_Machine", "Ab_Roller", "Adductor")
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
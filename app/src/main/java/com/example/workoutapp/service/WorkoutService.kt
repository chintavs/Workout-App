package com.example.workoutapp.service

import com.example.workoutapp.RetrofitClientInstance
import com.example.workoutapp.dao.IExerciseDAO
import com.example.workoutapp.dto.WorkoutRec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


class WorkoutService {



    suspend fun fetchWorkouts() : List<WorkoutRec>? {
        return withContext(Dispatchers.IO) {
            val service = RetrofitClientInstance.retrofitInstance?.create(IExerciseDAO::class.java)
            val plants = async { service?.getAllExercises("3_4_Sit-Up") }
            return@withContext plants.await()?.awaitResponse()?.body()
        }
    }
}
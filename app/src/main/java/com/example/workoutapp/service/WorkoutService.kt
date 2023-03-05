package com.example.workoutapp.service

import com.example.workoutapp.RetrofitClientInstance
import com.example.workoutapp.dao.IWorkoutDAO
import com.example.workoutapp.dto.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

interface IWorkoutService {
    suspend fun fetchWorkouts() : List<Workout>?
}
class WorkoutService : IWorkoutService {

    override suspend fun fetchWorkouts() : List<Workout>? {
        return withContext(Dispatchers.IO) {
            val service = RetrofitClientInstance.retrofitInstance?.create(IWorkoutDAO::class.java)
            val workouts = async {service?.getAllWorkouts()}
            var result = workouts.await()?.awaitResponse()?.body()
            return@withContext result
        }
    }
}
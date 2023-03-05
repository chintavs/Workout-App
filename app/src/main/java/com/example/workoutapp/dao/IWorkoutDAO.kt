package com.example.workoutapp.dao

import com.example.workoutapp.dto.Workout
import retrofit2.Call
import retrofit2.http.GET

interface IWorkoutDAO {
    @GET("")
    fun getAllWorkouts(): Call<ArrayList<Workout>>
}
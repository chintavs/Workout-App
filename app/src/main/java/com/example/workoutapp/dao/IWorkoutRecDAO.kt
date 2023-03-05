package com.example.workoutapp.dao

import com.example.workoutapp.dto.WorkoutRec
import retrofit2.Call
import retrofit2.http.GET

interface IWorkoutRecDAO {
    @GET("")
    fun getAllWorkoutRecs(): Call<ArrayList<WorkoutRec>>
}
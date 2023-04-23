package com.example.workoutapp.dao

import com.example.workoutapp.dto.User
import retrofit2.Call
import retrofit2.http.GET

interface IUserDAO {

    @GET("users.json")
    fun getAllUsers() : Call<ArrayList<User>>
}
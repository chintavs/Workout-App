package com.example.workoutapp.dao

import com.example.workoutapp.dto.User
import retrofit2.Call
import retrofit2.http.GET

interface IUserDAO {

    //Paste JSON URL (RetrofitClientInstance.BASE_URL) in @GET
    @GET("")
    fun getAllUsers() : Call<ArrayList<User>>
}
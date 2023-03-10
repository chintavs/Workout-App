package com.example.workoutapp.dao

import com.example.workoutapp.dto.Group
import retrofit2.Call
import retrofit2.http.GET

interface IGroupDAO {

    //Paste JSON URL (RetrofitClientInstance.BASE_URL) in @GET
    @GET("")
    fun getAllGroups() : Call<ArrayList<Group>>
}
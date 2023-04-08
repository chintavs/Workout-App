package com.example.workoutapp.dao

import com.example.workoutapp.dto.Group
import retrofit2.Call
import retrofit2.http.GET

interface IGroupDAO {

    @GET("groups.json")
    fun getAllGroups() : Call<ArrayList<Group>>
}
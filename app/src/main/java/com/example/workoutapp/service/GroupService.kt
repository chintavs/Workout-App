package com.example.workoutapp.service

import com.example.workoutapp.RetrofitClientInstance
import com.example.workoutapp.dao.IGroupDAO
import com.example.workoutapp.dto.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class GroupService {

    suspend fun fetchGroup() : List<Group>?{
        return withContext(Dispatchers.IO){
            val service = RetrofitClientInstance.retrofitInstance?.create(IGroupDAO::class.java)
            val groups = async{service?.getAllGroups()}
            var result = groups.await()?.awaitResponse()?.body()
            return@withContext result
        }
    }
}
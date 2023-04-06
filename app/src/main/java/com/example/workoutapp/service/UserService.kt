package com.example.workoutapp.service

import com.example.workoutapp.RetrofitClientInstance
import com.example.workoutapp.RetrofitClientInstanceUserandGroup
import com.example.workoutapp.dao.IUserDAO
import com.example.workoutapp.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class UserService {

    suspend fun fetchUser() : List<User>?{
        return withContext(Dispatchers.IO){
            val service = RetrofitClientInstanceUserandGroup.retrofitInstance?.create(IUserDAO::class.java)
            val users = async{service?.getAllUsers()}
            var result = users.await()?.awaitResponse()?.body()
            return@withContext result
        }
    }
}
package com.example.workoutapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    //Paste the JSON URL below
    private val BASE_URL = ""

    val retrofitInstance : Retrofit?
        get(){
            //has this object been created yet?
            if(retrofit == null){
                //create the object
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
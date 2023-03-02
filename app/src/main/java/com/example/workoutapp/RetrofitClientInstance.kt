package com.example.workoutapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    //Paste the JSON URL with mock data below
    private val BASE_URL = ""

    val retrofitInstance : Retrofit?
        get(){
            //Has this object been created yet?
            if(retrofit == null){
                //Create the object
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
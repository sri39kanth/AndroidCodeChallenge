package com.example.androidcodechallenge

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object NetworkClass {

     val retrofit : NetworkInterface = Retrofit.Builder()
        .baseUrl("https://randomuser.me")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkInterface::class.java)

    interface NetworkInterface {
        @GET("/api/?results=10")
        suspend fun getResponseData(): retrofit2.Response<UsersDataModel>
    }
}
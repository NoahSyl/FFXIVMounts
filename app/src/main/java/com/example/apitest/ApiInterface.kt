package com.example.apitest

import com.example.apitest.model.data
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    //Aqui posem les operacions GET, POST, PUT I DELETE que hem vist abans
    @GET("mounts/")
    suspend fun getData(): Response<data>


    companion object {
        val BASE_URL = "https://ffxivcollect.com/api/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit.create(ApiInterface::class.java)
        }

    }
}


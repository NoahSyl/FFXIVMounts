package com.example.apitest

class Repository {
    val apiInterface = ApiInterface.create()
    suspend fun getAllMounts() = apiInterface.getData()
}
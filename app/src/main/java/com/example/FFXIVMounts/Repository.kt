package com.example.FFXIVMounts

class Repository {
    val apiInterface = ApiInterface.create()
    suspend fun getAllMounts() = apiInterface.getData()
}
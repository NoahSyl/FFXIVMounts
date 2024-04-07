package com.example.apitest

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apitest.model.data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class MyViewModel : ViewModel() {

    private val repository = Repository()
    private val _mounts = MutableLiveData<data>()
    val mounts = _mounts
    fun getMounts() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllMounts()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _mounts.value = response.body()
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

}

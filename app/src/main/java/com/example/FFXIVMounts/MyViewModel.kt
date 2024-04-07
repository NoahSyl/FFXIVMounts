package com.example.FFXIVMounts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.FFXIVMounts.model.data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

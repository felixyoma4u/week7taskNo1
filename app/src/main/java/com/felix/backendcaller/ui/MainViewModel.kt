package com.felix.backendcaller.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felix.backendcaller.api.RetrofitProvider
import com.felix.backendcaller.api.RetrofitProvider.placeholderAPI
import com.felix.backendcaller.models.Item
import com.felix.backendcaller.models.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val itemsLiveData = MutableLiveData<List<Item>>()

    fun getItems(){
        CoroutineScope(Dispatchers.IO).launch {
            itemsLiveData.postValue(RetrofitProvider.placeholderAPI.getItems())
        }
    }

    fun addStudent(studentData: Student, onResult: (Student) -> Unit){
        placeholderAPI.addStudent(studentData).enqueue(
            object : Callback<Student> {
                override fun onFailure(call: Call<Student>, t: Throwable) {
                    Log.e(ContentValues.TAG, "Fail to Post Student Data")
                }

                override fun onResponse(call: Call<Student>, response: Response<Student>) {
                    val addedStudent = response.body()!!
                    onResult(addedStudent)
                }


            }
        )
    }
}
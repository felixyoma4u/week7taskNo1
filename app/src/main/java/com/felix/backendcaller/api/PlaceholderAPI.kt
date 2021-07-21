package com.felix.backendcaller.api

import com.felix.backendcaller.models.Item
import com.felix.backendcaller.models.Student
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaceholderAPI {

    @GET("items")
    suspend fun getItems(): List<Item>

    @POST("students")
    fun addStudent(@Body studentData : Student): Call<Student>
}
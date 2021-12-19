package com.example.foodloverscapston2.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealBuilder {
    private const val BASE_URL = "https://www.themealdb.com"
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mealAPI: MealAPI = retrofit().create(MealAPI::class.java)
}
package com.example.foodloverscapston2.data.network

import com.example.foodloverscapston2.data.models.Meals
import retrofit2.http.GET

interface MealAPI {

  @GET("/api/json/v2/9973533/latest.php")
       suspend fun fetchPhotos(): Meals
}
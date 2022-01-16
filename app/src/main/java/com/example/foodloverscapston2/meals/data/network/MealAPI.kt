package com.example.foodloverscapston2.meals.data.network

import com.example.foodloverscapston2.meals.data.models.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

  @GET("/api/json/v2/9973533/latest.php")
       suspend fun fetchPhotos(): Meals

//  @GET("/api/json/v2/1/search.php?")
//
//  suspend fun fetchSearch(@Query("s")name:String) : Meals
}
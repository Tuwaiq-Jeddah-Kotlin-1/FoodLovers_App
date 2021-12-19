package com.example.foodloverscapston2.data.network

import com.example.foodloverscapston2.data.models.Meals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealRepo {
    private val api = MealBuilder.mealAPI

    suspend fun fetchInterestingList():Meals = withContext(Dispatchers.IO){
        api.fetchPhotos()
    }

}
package com.example.foodloverscapston2.meals.data.network

import com.example.foodloverscapston2.meals.data.models.Meals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealRepo {
    private val api = MealBuilder.mealAPI

    suspend fun fetchMeals(): Meals = withContext(Dispatchers.IO) {
        api.fetchPhotos()
    }

    suspend fun fetchSearch(name:String): Meals = withContext(Dispatchers.IO){
        api.fetchSearch(name)
    }

}
package com.example.foodloverscapston2.data.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodloverscapston2.data.models.Meals
import com.example.foodloverscapston2.data.network.MealRepo
import kotlinx.coroutines.launch

class MealsViewModle : ViewModel() {
    val repo = MealRepo()

    fun fetchInterestingList(): LiveData<Meals> {
        var meals = MutableLiveData<Meals>()
        viewModelScope.launch {
            try {
                meals.postValue(repo.fetchInterestingList())

            }catch (e: Throwable){

                Log.e("Meals"," problem: ${e.localizedMessage}")
            }
        }
        return meals
    }
}
package com.example.foodloverscapston2.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Meals (
    val meals:List<MealsData>
        )
@Parcelize
data class MealsData(
    val strMeal : String,
    val strInstructions : String,
    val strMealThumb : String,
    val strYoutube : String,
    val strIngredient1 : String,
    val strIngredient2 : String,
    val strIngredient3 : String,
    val strIngredient4 : String,
    val strIngredient5 : String,
    val strIngredient6 : String,
    val strIngredient7 : String,
    val strIngredient8 : String,
    val strIngredient9 : String,
    val strIngredient10 : String,
    val strIngredient11 : String,
    val strIngredient12 : String,
    val strIngredient13 : String,
    val strIngredient14 : String,
    val strIngredient15 : String,
    val strIngredient16 : String,
    val strIngredient17 : String,
    val strIngredient18 : String,
    val strIngredient19 : String,
    val strIngredient20 : String,
    val strMeasure1 : String,
    val strMeasure2 : String,
    val strMeasure3 : String,
    val strMeasure4 : String,
    val strMeasure5 : String,
    val strMeasure6 : String,
    val strMeasure7 : String,
    val strMeasure8 :String,
    val strMeasure9 : String,
    val strMeasure10 : String,
    val strMeasure11 : String,
    val strMeasure12 : String,
    val strMeasure13 : String,
    val strMeasure14 : String,
    val strMeasure15 : String,
    val strMeasure16 : String,
    val strMeasure17 : String,
    val strMeasure18 : String,
    val strMeasure19 : String,
    val strMeasure20 : String,
  ):Parcelable
//    {
//
//    var ingr = listOf<String>( strIngredient1, strIngredient2, strIngredient3, strIngredient4,strIngredient5,
//        strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11,
//        strIngredient12, strIngredient13, strIngredient14, strIngredient15, strIngredient16, strIngredient17,
//        strIngredient18, strIngredient19, strIngredient20)
//    var meas = listOf(
//        "receivedData.strMeasure1", "receivedData.strMeasure2", "receivedData.strMeasure3",
//        "receivedData.strMeasure4", "receivedData.strMeasure5", "receivedData.strMeasure6",
//        "receivedData.strMeasure7", "receivedData.strMeasure8", "receivedData.strMeasure9",
//        "receivedData.strMeasure10", "receivedData.strMeasure11", "receivedData.strMeasure12",
//        "receivedData.strMeasure13", "receivedData.strMeasure14", "receivedData.strMeasure15",
//        "receivedData.strMeasure16", "receivedData.strMeasure17", "receivedData.strMeasure18",
//        "receivedData.strMeasure19", "receivedData.strMeasure20"
//    )
//}
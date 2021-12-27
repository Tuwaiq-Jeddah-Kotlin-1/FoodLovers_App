package com.example.foodloverscapston2.data.models

import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val current = LocalDateTime.now()
val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm")
val formatted = current.format(formatter)
@Parcelize
data class Recipe (
    var recipeName: String = "",
    var recipeInstructions: String = "",
    var recipeID : String = "",
    var dateRecipe : String="$formatted"
        ): Parcelable
{ companion object{
    fun DocumentSnapshot.toRecipe(): Recipe? {
        try {
            val recipeName = getString("recipeName")!!
            val recipeInstructions = getString("recipeInstructions")!!
            val recipeID= getString("recipeID")!!
            val dateRecipe = getString("dateRecipe")!!

            return Recipe(recipeName ,recipeInstructions, recipeID, dateRecipe)
        } catch (e: Exception) {
            Log.e(TAG, "Error ", e)
            return null
        } }
    private const val TAG = "Recipe"
}}





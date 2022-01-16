package com.example.foodloverscapston2.recipe.data

import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH")
    val formatted = current.format(formatter)
@Parcelize
data class Recipe (
    var recipeName: String = "",
    var recipeInstructions: String = "",
    var recipeID : String = "",
    var dateRecipe : String="$formatted",
    var image : String=""
        ): Parcelable
{ companion object{
    fun DocumentSnapshot.toRecipe(): Recipe? {
        return try {
            val recipeName = getString("recipeName")!!
            val recipeInstructions = getString("recipeInstructions")!!
            val recipeID= getString("recipeID")!!
            val dateRecipe = getString("dateRecipe")!!
            val image = getString("image")!!

            Recipe(recipeName ,recipeInstructions, recipeID, dateRecipe, image)
        } catch (e: Exception) {
            Log.e(TAG, "Error ", e)
            null
        }
    }
    private const val TAG = "Recipe"
}}





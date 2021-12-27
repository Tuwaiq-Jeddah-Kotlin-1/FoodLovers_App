package com.example.foodloverscapston2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.foodloverscapston2.R


class DetailsFragment : Fragment() {
    private lateinit var meal_n: TextView
    private lateinit var meal_ingredients: TextView
    private lateinit var t_ingredients: TextView
    private lateinit var t_measures: TextView
    private lateinit var meal_instructions: TextView
    private lateinit var tv_instructions: TextView
    private lateinit var imgg_meal: ImageView
    private lateinit var youtubeLink: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args: DetailsFragmentArgs by navArgs()
        val receivedData = args.mealsDis

        meal_n = view.findViewById(R.id.meal_n)
        meal_ingredients = view.findViewById(R.id.meal_ingredients)
        t_ingredients = view.findViewById(R.id.t_ingredients)
        t_measures = view.findViewById(R.id.t_measures)
        meal_instructions = view.findViewById(R.id.meal_instructions)
        tv_instructions = view.findViewById(R.id.tv_instructions)
        imgg_meal = view.findViewById(R.id.imgg_meal)
        youtubeLink = view.findViewById(R.id.youtubeLink)

        meal_n.text = receivedData.strMeal
        tv_instructions.text = receivedData.strInstructions
        imgg_meal.load(receivedData.strMealThumb)


//       youtubeLink.setOnClickListener {
//
//     }
//        t_ingredients.text = receivedData.strIngredient1
//        t_ingredients.text = receivedData.strIngredient2
//        t_ingredients.text = receivedData.strIngredient3
//        t_ingredients.text = receivedData.strIngredient4
//        t_ingredients.text = receivedData.strIngredient5
//        t_ingredients.text = receivedData.strIngredient6
//        t_ingredients.text = receivedData.strIngredient7
//        t_ingredients.text = receivedData.strIngredient8
//        t_ingredients.text = receivedData.strIngredient9
//        t_ingredients.text = receivedData.strIngredient10
//        t_ingredients.text = receivedData.strIngredient11
//        t_ingredients.text = receivedData.strIngredient12
//        t_ingredients.text = receivedData.strIngredient13
//        t_ingredients.text = receivedData.strIngredient14
//        t_ingredients.text = receivedData.strIngredient15
//        t_ingredients.text = receivedData.strIngredient16
//        t_ingredients.text = receivedData.strIngredient17
//        t_ingredients.text = receivedData.strIngredient18
//        t_ingredients.text = receivedData.strIngredient19
//        t_ingredients.text = receivedData.strIngredient20
//        t_ingredients_measures.text = receivedData.strMeasure1
//        t_ingredients_measures.text = receivedData.strMeasure2
//        t_ingredients_measures.text = receivedData.strMeasure3
//        t_ingredients_measures.text = receivedData.strMeasure4
//        t_ingredients_measures.text = receivedData.strMeasure5
//        t_ingredients_measures.text = receivedData.strMeasure6
//        t_ingredients_measures.text = receivedData.strMeasure7
//        t_ingredients_measures.text = receivedData.strMeasure8
//        t_ingredients_measures.text = receivedData.strMeasure9
//        t_ingredients_measures.text = receivedData.strMeasure10
//        t_ingredients_measures.text = receivedData.strMeasure11
//        t_ingredients_measures.text = receivedData.strMeasure12
//        t_ingredients_measures.text = receivedData.strMeasure13
//        t_ingredients_measures.text = receivedData.strMeasure14
//        t_ingredients_measures.text = receivedData.strMeasure15
//        t_ingredients_measures.text = receivedData.strMeasure16
//        t_ingredients_measures.text = receivedData.strMeasure17
//        t_ingredients_measures.text = receivedData.strMeasure18
//        t_ingredients_measures.text = receivedData.strMeasure19
//        t_ingredients_measures.text = receivedData.strMeasure20

    }

}


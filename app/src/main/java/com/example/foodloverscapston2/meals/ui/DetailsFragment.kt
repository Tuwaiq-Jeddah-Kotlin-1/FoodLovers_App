package com.example.foodloverscapston2.meals.ui


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.foodloverscapston2.R
import com.google.common.base.Strings.isNullOrEmpty


class DetailsFragment : Fragment() {

    private lateinit var meal_n: TextView
    private lateinit var meal_ingredients: TextView
    private lateinit var tv_ingredients: TextView
    private lateinit var tv_measures: TextView
    private lateinit var meal_instructions: TextView
    private lateinit var tv_instructions: TextView
    private lateinit var imgg_meal: ImageView
    private lateinit var youtubeLink: LinearLayout
    private var info: String = ""
    private var info2: String = ""

    var ingr = mutableListOf<Any>()
    var meas = mutableListOf<Any>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: DetailsFragmentArgs by navArgs()
        val receivedData = args.mealsDis

        ingr = mutableListOf(
            receivedData.strIngredient1,
            receivedData.strIngredient2,
            receivedData.strIngredient3,
            receivedData.strIngredient4,
            receivedData.strIngredient5,
            receivedData.strIngredient6,
            receivedData.strIngredient7,
            receivedData.strIngredient8,
            receivedData.strIngredient9,
            receivedData.strIngredient10,
            receivedData.strIngredient11,
            receivedData.strIngredient12,
            receivedData.strIngredient13,
            receivedData.strIngredient14,
            receivedData.strIngredient15,
            receivedData.strIngredient16,
            receivedData.strIngredient17,
            receivedData.strIngredient18,
            receivedData.strIngredient19,
            receivedData.strIngredient20
        )

        meas = mutableListOf(
            receivedData.strMeasure1, receivedData.strMeasure2, receivedData.strMeasure3,
            receivedData.strMeasure4, receivedData.strMeasure5, receivedData.strMeasure6,
            receivedData.strMeasure7, receivedData.strMeasure8, receivedData.strMeasure9,
            receivedData.strMeasure10, receivedData.strMeasure11, receivedData.strMeasure12,
            receivedData.strMeasure13, receivedData.strMeasure14, receivedData.strMeasure15,
            receivedData.strMeasure16, receivedData.strMeasure17, receivedData.strMeasure18,
            receivedData.strMeasure19, receivedData.strMeasure20
        )

        meal_n = view.findViewById(R.id.meal_n)
        meal_ingredients = view.findViewById(R.id.meal_ingredients)
        tv_ingredients = view.findViewById(R.id.t_ingredients)
        tv_measures = view.findViewById(R.id.t_measures)
        meal_instructions = view.findViewById(R.id.meal_instructions)
        tv_instructions = view.findViewById(R.id.tv_instructions)
        imgg_meal = view.findViewById(R.id.imgg_meal)
        youtubeLink = view.findViewById(R.id.ll_youtube)

        tv_ingredients.text = getInfo()
        tv_measures.text = getInfo2()
        meal_n.text = "(${receivedData.strMeal})"
        tv_instructions.text = receivedData.strInstructions
        imgg_meal.load(receivedData.strMealThumb)

        youtubeLink.setOnClickListener {
            val url = "${receivedData.strYoutube}"
            val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            val intentAbb = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "vnd.youtube:" + url
                        .substring(url.indexOf("v=") + 2)
                )
            )
            try {
                it.context.startActivity(intentAbb)

            } catch (ex: ActivityNotFoundException) {
                it.context.startActivity(intentWeb)

            }
        }
    }


    fun getInfo(): String {
        for (i in 0..19) {
            if (ingr[i] != null) {
                if (ingr[i].toString().isNotBlank()) {
                    info += ingr[i].toString() + "\n"
                }
            }
        }
        return info
    }


    fun getInfo2(): String {
        for (i in 0..19) {
            if (meas[i] != null) {
                if (meas[i].toString().isNotBlank()) {
                    info2 += meas[i].toString() + "\n"
                }
            }
        }
        return info2
    }
}





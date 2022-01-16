package com.example.foodloverscapston2.recipe.ui

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


class AddRecipeDetailsFragment : Fragment() {

    private lateinit var imgg_recipe : ImageView
    private lateinit var recipe_n : TextView
    private lateinit var recipe_instructions : TextView
    private lateinit var rtv_instructions : TextView
    private val args by navArgs<AddRecipeDetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgg_recipe = view.findViewById(R.id.imgg_recipe)
        recipe_n = view.findViewById(R.id.recipe_n)
        recipe_instructions = view.findViewById(R.id.recipe_instructions)
        rtv_instructions = view.findViewById(R.id.rtv_instructions)
        imgg_recipe.load(args.detailsdata.image)
        recipe_n.text = args.detailsdata.recipeName
        rtv_instructions.text = args.detailsdata.recipeInstructions
    }

    }

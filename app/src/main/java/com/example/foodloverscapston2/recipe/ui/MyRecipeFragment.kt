package com.example.foodloverscapston2.recipe.ui

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.recipe.data.Recipe
import com.example.foodloverscapston2.recipe.data.Recipe.Companion.toRecipe
import com.example.foodloverscapston2.recipe.adapter.RecipeAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MyRecipeFragment : Fragment() {

    private lateinit var newRecipeButton: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth
    private lateinit var noRecipe : ImageView
    private lateinit var tvNoRecipe : TextView

         override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
             auth = FirebaseAuth.getInstance()
    }
         override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
             savedInstanceState: Bundle?
             ): View? {

        val view = inflater.inflate(R.layout.fragment_my_recipe,container, false)
        val db = FirebaseFirestore.getInstance()

             noRecipe = view.findViewById(R.id.noRecipe)
            tvNoRecipe= view.findViewById(R.id.tvNoRecipe)

        try {
            auth.currentUser?.let { it1 ->
                db.collection("users").document(it1.uid)
                    .collection("listofrecipe")
                    .get()
                    .addOnSuccessListener {
                        val recipeList = mutableListOf<Recipe>()

                        it.forEach {
                            it.toRecipe()?.let { it2 ->
                                recipeList.add(it2)
                            }
            if(recipeList.isNotEmpty()) {
                noRecipe.visibility = View.GONE
                tvNoRecipe.visibility = View.GONE
        }
           recyclerView.adapter = RecipeAdapter (recipeList, noRecipe, tvNoRecipe)

                        }
                    }
            }
        } catch (e: Exception) { }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(context)

        newRecipeButton = view.findViewById(R.id.newRecipeButton)


        newRecipeButton.setOnClickListener {

            findNavController().navigate(R.id.actionMyRecipeFragmentToAddFragment)
        }

    }

}
package com.example.foodloverscapston2.recipe.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.recipe.data.Recipe
import com.example.foodloverscapston2.recipe.data.Recipe.Companion.toRecipe
import com.example.foodloverscapston2.recipe.adapter.RecipeAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MyRecipeFragment : Fragment() {

    private lateinit var newRecipeButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
     savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_my_recipe,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val search = view.findViewById<SearchView>(R.id.serachView)

        recyclerView = view.findViewById(R.id.rvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val db = FirebaseFirestore.getInstance()

//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.d(tag, "Query text : $query")
//                loadBooks(query?.trim())
//                return true
//            }

//            override fun onQueryTextChange(newText: String?): Boolean {
//                Log.d(tag, "Query text : $newText")
//                return false
//            }
//        })
        try {
            auth.currentUser?.let { it1 ->
                db.collection("users").document(it1.uid)
                    .collection("listofrecipe")
                    .get()
                    .addOnSuccessListener {
                        var recipeList = mutableListOf<Recipe>()
                        it.forEach {
                            it.toRecipe()?.let { it2 ->
                                recipeList.add(it2)
                            }
                            recyclerView.adapter = RecipeAdapter (recipeList)
                        }
                    }
            }
        } catch (e: Exception) {

        }

        newRecipeButton = view.findViewById(R.id.newRecipeButton)

        newRecipeButton.setOnClickListener {

            findNavController().navigate(R.id.actionMyRecipeFragmentToAddFragment)
        }

    }

}
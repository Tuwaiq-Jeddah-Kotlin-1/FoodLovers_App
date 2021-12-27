package com.example.foodloverscapston2.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.models.Recipe
import com.example.foodloverscapston2.data.models.Recipe.Companion.toRecipe
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

        recyclerView = view.findViewById(R.id.rvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val db = FirebaseFirestore.getInstance()

        try {
            auth.currentUser?.let { it1 ->
                db.collection("users").document(it1.uid)
                    .collection("listofrecipe")
                    .get()
                    .addOnSuccessListener {
                        var l = mutableListOf<Recipe>()
                        it.forEach {
                            it.toRecipe()?.let { it2 ->
                                l.add(it2)
                            }
                            recyclerView.adapter = RecipeAdapter (l)
                        }
                    }
            }
        } catch (e: Exception) {

        }
        newRecipeButton = view.findViewById(R.id.newRecipeButton)

        newRecipeButton.setOnClickListener {

            findNavController().navigate(R.id.actionMyRecipeFragmentToAddFragment)
        }


}}
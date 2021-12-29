package com.example.foodloverscapston2.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddFragment : Fragment() {

    private lateinit var addRecipeName : EditText
    private lateinit var addRecipeInstructions: EditText
    private lateinit var addButton : Button
    private lateinit var cancelButton : Button
    private var auth= Firebase.auth
    private  val db = Firebase.firestore
    var TAG = "MyRecipeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addRecipeName = view.findViewById(R.id.addRecipeName)
        addRecipeInstructions = view.findViewById(R.id.addRecipeInstructions)
        addButton = view.findViewById(R.id.addButton)
        cancelButton = view.findViewById(R.id.cancelButton)

        addButton.setOnClickListener {

            if (addRecipeName.text.toString()
                    .isNullOrEmpty() && addRecipeInstructions.text.toString().isNullOrEmpty()
            ) {
             Toast.makeText(context,getString(R.string.Enter_recipe_name_and_instruction),Toast.LENGTH_LONG).show()
            } else {

                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-SS")
                val formatted = current.format(formatter)
            val add = Recipe()
            add.recipeInstructions = addRecipeInstructions.text.toString()
            add.recipeName = addRecipeName.text.toString()
            var userId = FirebaseAuth.getInstance().currentUser?.uid
            add.recipeID = "${userId.toString()}${formatted}"

            auth.currentUser?.let { it1 ->
                db.collection("users").document(it1.uid).collection("listofrecipe")
                    .document("${add.recipeID}")
                    .set(add)
                    .addOnSuccessListener { Log.d(TAG, getString(R.string.recipe_added_successfully))}
                    .addOnFailureListener { e -> Log.w(TAG, getString(R.string.add_recipe), e) }
            }
            findNavController().navigate(R.id.actionAddFragmentToMyRecipeFragment)
        }
        }

        cancelButton.setOnClickListener{
            findNavController().navigate(R.id.actionAddFragmentToMyRecipeFragment)
        }


    }

            }

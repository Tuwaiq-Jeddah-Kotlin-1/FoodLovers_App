package com.example.foodloverscapston2.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class EditFragment : Fragment() {

    private lateinit var editRecipeName : EditText
    private lateinit var editRecipeInstructions: EditText
    private lateinit var updateButton : Button
    private lateinit var cancellButton : Button
    private var auth= Firebase.auth
    private  val db = Firebase.firestore
    var TAG = "MyRecipeFragment"
    private val args by navArgs<EditFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editRecipeName = view.findViewById(R.id.editRecipeName)
        editRecipeInstructions = view.findViewById(R.id.editRecipeInstructions)
       updateButton = view.findViewById(R.id.updateButton)
       cancellButton = view.findViewById(R.id.cancellButton)

        editRecipeName.setText(args.editdata.recipeName)
        editRecipeInstructions.setText(args.editdata.recipeInstructions)

        updateButton.setOnClickListener{
           val uld = FirebaseAuth.getInstance().currentUser?.uid

            val upDateUserData = Firebase.firestore.collection("users")
            upDateUserData.document(uld.toString()).collection("listofrecipe").document(args.editdata.recipeID.toString())
                .update("recipeName",editRecipeName.text
          .toString(),"recipeInstructions",editRecipeInstructions.text.toString())
                .addOnSuccessListener { Log.d(TAG, "Recipe Update successfully ") }
                .addOnFailureListener { e -> Log.w(TAG, getString(R.string.there_are_no_changes), e) }
            findNavController().navigate(R.id.actionEditFragmentToMyRecipeFragment)
        }


        cancellButton.setOnClickListener{
            findNavController().navigate(R.id.actionEditFragmentToMyRecipeFragment)
        }
    }

    }

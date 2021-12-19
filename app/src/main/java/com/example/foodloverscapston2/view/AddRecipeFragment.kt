package com.example.foodloverscapston2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R


class AddFragment : Fragment() {

    private lateinit var addRecipeName : EditText
    private lateinit var addRecipeInstructions: EditText
    private lateinit var addButton : Button
    private lateinit var cancelButton : Button

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

        addButton.setOnClickListener{
            findNavController().navigate(R.id.actionAddFragmentToMyRecipeFragment)
        }
        cancelButton.setOnClickListener{
            findNavController().navigate(R.id.actionAddFragmentToMyRecipeFragment)
        }


    }

            }

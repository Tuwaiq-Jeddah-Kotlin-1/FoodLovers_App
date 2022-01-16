package com.example.foodloverscapston2.recipe.ui

import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.recipe.data.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AddFragment : Fragment() {

    private lateinit var addRecipeName: EditText
    private lateinit var addRecipeInstructions: EditText
    private lateinit var picture: ImageView
    private lateinit var addButton: Button
    private lateinit var cancelButton: Button
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    var TAG = "MyRecipeFragment"
    private var img: Uri? = null
    private lateinit var progress: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = ProgressDialog(context)
        progress.setTitle("Please wait")
        progress.setMessage("adding recipe")
        progress.setCanceledOnTouchOutside(false)

        addRecipeName = view.findViewById(R.id.addRecipeName)
        addRecipeInstructions = view.findViewById(R.id.addRecipeInstructions)
        picture = view.findViewById(R.id.pic)
        addButton = view.findViewById(R.id.addButton)
        cancelButton = view.findViewById(R.id.cancelButton)
        auth = FirebaseAuth.getInstance()
        val getActionSelectImage =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                img = it.data?.data!!
            }
        picture.setOnClickListener {
            val selectImageIntent = Intent()
            selectImageIntent.type = "image/*"
            selectImageIntent.action = Intent.ACTION_GET_CONTENT
            try {
                if (activity?.let { it1 -> selectImageIntent.resolveActivity(it1.packageManager) } != null) {
                    getActionSelectImage.launch(selectImageIntent)

                }
            } catch (e: ActivityNotFoundException) {
       }

        }

        addButton.setOnClickListener {

            if (addRecipeName.text.toString()
                    .isNullOrEmpty() || addRecipeInstructions.text.toString()
                    .isNullOrEmpty() || img == null
            ) {
                Toast.makeText(
                    context,
                    getString(R.string.Enter_recipe_name_and_instruction),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                addPic(img!!,view)

            }
        }

        cancelButton.setOnClickListener {
            findNavController().navigate(R.id.actionAddFragmentToMyRecipeFragment)
        }
    }

    private fun addPic(imageUri: Uri, view: View) {
        val formatter = SimpleDateFormat("yyy-MM-dd-mm-ss")
        val now = Date()
        val fileName = formatter.format(now)
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$fileName.jpg")
        progress.show()

        try {
            storageRef.putFile(imageUri)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference?.downloadUrl?.addOnSuccessListener {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-SS")
                        val formatted = current.format(formatter)
                        val add = Recipe()
                        add.recipeInstructions = addRecipeInstructions.text.toString()
                        add.recipeName = addRecipeName.text.toString()
                        var userId = FirebaseAuth.getInstance().currentUser?.uid
                        add.recipeID = "${userId.toString()}${formatted}"
                        add.image = it.toString()
                        auth.currentUser?.let { it1 ->
                            db.collection("users").document(it1.uid).collection("listofrecipe")
                                .document("${add.recipeID}")
                                .set(add)
                               .addOnSuccessListener {
                                  Log.e(TAG, view.context.getString(R.string.recipe_added_successfully))
                                   progress.dismiss()
                                   findNavController().navigate(R.id.actionAddFragmentToMyRecipeFragment)
                               }
                                .addOnFailureListener { e -> Log.w(TAG, getString(R.string.add_recipe), e) }
                        }

                    }?.addOnFailureListener {
                        Log.e("Error", "can't uplode", it)
                    }
                }
        } catch (e: Exception) {
            Log.e("exception", "exception failure", e)
        }
    }

}

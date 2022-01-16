package com.example.foodloverscapston2.recipe.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.recipe.data.Recipe
import com.example.foodloverscapston2.recipe.ui.MyRecipeFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecipeAdapter (val dataAdded : MutableList<Recipe>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

   val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val recipeRC = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item,parent,false)
        return RecipeViewHolder(recipeRC)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        var recipedata = dataAdded[position]
        holder.recipeName.text = recipedata.recipeName
        holder.idRecipe = recipedata.recipeID
        holder.addedImage1.load(recipedata.image)

        holder.itemView.setOnClickListener {
            val recipe_data =Recipe()

            recipe_data.recipeName = recipedata.recipeName
            recipe_data.recipeInstructions = recipedata.recipeInstructions
            recipe_data.recipeID = holder.idRecipe
            recipe_data.image = recipedata.image

            val action:NavDirections = MyRecipeFragmentDirections
            .actionMyRecipeFragmentToAddRecipeDetailsFragment(recipe_data)
            it.findNavController().navigate(action)
        }

        holder.editIcon.setOnClickListener {
            val recipe_data =Recipe()

            recipe_data.recipeName = recipedata.recipeName
            recipe_data.recipeInstructions = recipedata.recipeInstructions
            recipe_data.recipeID = holder.idRecipe

            val action:NavDirections = MyRecipeFragmentDirections
                .actionMyRecipeFragmentToEditFragment(recipe_data)
            it.findNavController().navigate(action)
        }

        holder.deleteRecipe.setOnClickListener {
          val deleteRecipe = Firebase.firestore.collection("users")
              .document("${userId.toString()}").collection("listofrecipe")
              .document(holder.idRecipe.toString()).delete()
            dataAdded.removeAt(position)
              notifyItemRemoved(position)
            notifyItemRangeChanged(position, getItemCount())
        }

           val url = "${recipedata.recipeName}:\n${recipedata.recipeInstructions}"
            val intent = Intent(Intent.ACTION_SEND)
            holder.shareRecipe.setOnClickListener {
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, url)
                intent.putExtra(Intent.EXTRA_TEXT, url)
                it.context.startActivity(Intent.createChooser(intent, "Share using"))
            }
    }

    override fun getItemCount(): Int {
        return dataAdded.size
    }
    class RecipeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val recipeName : TextView = itemView.findViewById(R.id.recipeName)
        val shareRecipe: ImageView = itemView.findViewById(R.id.shareIcon)
        val deleteRecipe : ImageView = itemView.findViewById(R.id.delete)
        val addedImage1 : ImageView = itemView.findViewById(R.id.addedImage1)
        val editIcon : ImageView = itemView.findViewById(R.id.editIcon)
        lateinit var idRecipe : String
    }
}
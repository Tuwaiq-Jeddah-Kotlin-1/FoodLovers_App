package com.example.foodloverscapston2.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.models.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecipeAdapter (val dataAdded : MutableList<Recipe>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

   private var auth= Firebase.auth
   private  val db = Firebase.firestore
   var TAG = "MyRecipeFragment"
   val userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val rc = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_item,parent,false)
        return RecipeViewHolder(rc)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {

        var recipedata = dataAdded[position]
        holder.recipeName.text = recipedata.recipeName
//       holder.recipeInstructions.text = recipedata.recipeInstructions
      holder.idRe = recipedata.recipeID

        holder.detailsRecipe.setOnClickListener {
            val recipe_data =Recipe()

            recipe_data.recipeName = recipedata.recipeName
            recipe_data.recipeInstructions = recipedata.recipeInstructions
            recipe_data.recipeID = holder.idRe
            val action:NavDirections = MyRecipeFragmentDirections
            .actionMyRecipeFragmentToEditFragment(recipe_data)
            it.findNavController().navigate(action)
        }

        holder.deleteRecipe.setOnClickListener {
          val deleteRecipe = Firebase.firestore.collection("users")
              .document("${userId.toString()}").collection("listofrecipe")
              .document(holder.idRe.toString()).delete()
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
//        val recipeInstructions: TextView = itemView.findViewById(R.id.recipeInstructions)
        val detailsRecipe: TextView = itemView.findViewById(R.id.detailsIcon)
        val shareRecipe: ImageView = itemView.findViewById(R.id.shareIcon)
        val deleteRecipe : ImageView = itemView.findViewById(R.id.delete)
        lateinit var idRe : String
    }
}
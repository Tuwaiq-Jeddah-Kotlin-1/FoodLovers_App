package com.example.foodloverscapston2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.models.MealsData

class MealAdapter (val data: List<MealsData>):RecyclerView.Adapter<MealsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.explore_item,parent,false)
        return MealsViewHolder(v)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        var mealdata = data[position]
        holder.image.load(mealdata.strMealThumb)
        holder.recipeN.text = mealdata.strMeal
// send data to dis fragment
        holder.itemView.setOnClickListener {
            val action:NavDirections = ExplorerFragmentDirections.actionExplorerFragmentToDisFragment(mealdata)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
       return data.size
    }

}
class MealsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val image : ImageView = itemView.findViewById(R.id.addedImage)
    val recipeN :TextView = itemView.findViewById(R.id.recipeName)
}
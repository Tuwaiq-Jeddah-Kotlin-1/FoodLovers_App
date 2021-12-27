package com.example.foodloverscapston2.view

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
import com.example.foodloverscapston2.data.models.MealsData

class MealAdapter (val data: List<MealsData>):RecyclerView.Adapter<MealsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.explore_item,parent,false)
        return MealsViewHolder(v)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        var mealdata = data[position]
        holder.image.load(mealdata.strMealThumb)
        holder.mealsName.text = mealdata.strMeal
// send data to dis fragment
        holder.itemView.setOnClickListener {
            val action:NavDirections = ExplorerFragmentDirections.actionExplorerFragmentToDetailsFragment(mealdata)
            it.findNavController().navigate(action)
        }
        //link !
        val url = "${mealdata.strSource}"
        val intent = Intent(Intent.ACTION_SEND)
        holder.shareMeals.setOnClickListener {
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, url)
                intent.putExtra(Intent.EXTRA_TEXT, url)
                it.context.startActivity(Intent.createChooser(intent, "Share using"))
            }
        }

    override fun getItemCount(): Int {
       return data.size
    }

}
class MealsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val image : ImageView = itemView.findViewById(R.id.addedImage)
    val mealsName :TextView = itemView.findViewById(R.id.mealsName)
    val shareMeals: ImageView = itemView.findViewById(R.id.shareIconTwo)

}
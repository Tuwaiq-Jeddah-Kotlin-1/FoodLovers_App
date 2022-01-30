package com.example.foodloverscapston2.meals.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.foodloverscapston2.meals.MyWorker
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.meals.adapter.MealAdapter
import com.example.foodloverscapston2.meals.data.models.MealsData
import com.example.foodloverscapston2.meals.data.viewModels.MealsViewModel
import java.util.concurrent.TimeUnit

  var notifyList : List<MealsData>? = null

class ExplorerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vm: MealsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explorer, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = view.findViewById<SearchView>(R.id.searchView)

        recyclerView = view.findViewById(R.id.rvExplorer)
        recyclerView.layoutManager = LinearLayoutManager(context)

         vm = ViewModelProvider(this).get(MealsViewModel::class.java)

        vm.fetchMealsList().observe(viewLifecycleOwner,{
            recyclerView.adapter = MealAdapter(it.meals)

            notifyList = it.meals
            myWorkerManger()
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(tag, "Query text : $query")
                if(query?.trim()?.length!! >0){
                    loadMeals(query!!.trim())
                }else{
             Log.d(TAG,"Toast")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG,"QueryTextChange: $newText")
                return false

            }
        })
    }

    private fun loadMeals(query: String) {
        vm.fetchMealsList(query).observe(viewLifecycleOwner, {
                recyclerView.adapter = MealAdapter(it.meals)
            Log.d("Meals main Response", it.toString())

        })
    }

    private fun myWorkerManger() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()
        val myRequest = PeriodicWorkRequest.Builder(
            MyWorker::class.java,5, TimeUnit.SECONDS
        ).setConstraints(constraints)
            .build()
        WorkManager.getInstance(requireContext())
            .enqueueUniquePeriodicWork("my_id", ExistingPeriodicWorkPolicy.KEEP,myRequest)
        Log.e(ContentValues.TAG, "myWorkerManger: ", )
    }

}



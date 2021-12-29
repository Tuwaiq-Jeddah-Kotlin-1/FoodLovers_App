package com.example.foodloverscapston2.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.foodloverscapston2.MyWorker
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.models.MealsData
import com.example.foodloverscapston2.data.viewModels.MealsViewModle
import java.util.concurrent.TimeUnit

  var l : List<MealsData>? = null

class ExplorerFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rvExplorer)
        recyclerView.layoutManager = LinearLayoutManager(context)

        var vm = ViewModelProvider(this).get(MealsViewModle::class.java)

        vm.fetchInterestingList().observe(viewLifecycleOwner,{
            recyclerView.adapter = MealAdapter(it.meals)

            l = it.meals
            myWorkerManger()
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


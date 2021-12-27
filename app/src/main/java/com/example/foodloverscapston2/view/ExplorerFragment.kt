package com.example.foodloverscapston2.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.data.viewModels.MealsViewModle

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
        })
    }

}


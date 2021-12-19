package com.example.foodloverscapston2.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodloverscapston2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class MyRecipeFragment : Fragment() {

    private lateinit var newRecipeButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
     savedInstanceState: Bundle?
    ): View? {
        //menu
        setHasOptionsMenu(true)
        return view
    }
    //menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.setting_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
//            R.id.mood -> {
//                // write logic here
//            }
//            R.id.language -> {
//
//            }
           R.id.singout -> {
              auth.signOut()
               findNavController().navigate(R.id.actionMyRecipeFragmentToLoginFragment)
            }
        }
        return true }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvRecipe)
        recyclerView.layoutManager = LinearLayoutManager(context)
        var vm = ViewModelProvider(this).get(MainVM::class.java)

        vm.fetchInterestingList().observe(viewLifecycleOwner,{
            recyclerView.adapter = MealAdapter(it.meals)
        })

        newRecipeButton = view.findViewById(R.id.newRecipeButton)
        newRecipeButton.setOnClickListener {

            findNavController().navigate(R.id.actionMyRecipeFragmentToAddFragment)

        }

    }

}
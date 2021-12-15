package com.example.foodloverscapston2.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R
import com.google.firebase.auth.FirebaseAuth


class MyRecipeFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        return true
    }

}
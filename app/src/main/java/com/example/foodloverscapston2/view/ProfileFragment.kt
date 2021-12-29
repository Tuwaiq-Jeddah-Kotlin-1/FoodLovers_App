package com.example.foodloverscapston2.view

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import java.util.*


class ProfileFragment : Fragment() {
      private lateinit var username : TextView
      private lateinit var useremail: TextView
      private lateinit var singout: TextView
      private lateinit var language: Button

    var firebaseFirestore: FirebaseFirestore =FirebaseFirestore.getInstance()
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseUserId: String = auth.currentUser!!.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = view.findViewById(R.id.user_name)
        useremail = view.findViewById(R.id.user_email)
        singout = view.findViewById(R.id.sing_out)
        language = view.findViewById(R.id.language)

        singout.setOnClickListener{
            auth.signOut()
            findNavController().navigate(R.id.actionProfileFragmentToLoginFragment)
        }

        firebaseFirestore.collection("users")
            .document(firebaseUserId)
            .addSnapshotListener(object: EventListener<DocumentSnapshot> {

     override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
            if (error != null) {
                Log.e(
                 "TAG",
                "Firestore error in retrieving data" + error.message.toString()
                        )
                    } else {
                        value!!.apply {
                            username.text = value.getString("user_name")
                            useremail.text = value.getString("email")
                        }
                    }
     } }
            )
        language.setOnClickListener{
            showLangDialog()
        }
    }

    private fun refreshCurrentFragment(){
        val refresh = findNavController().currentDestination?.id
        findNavController().navigateUp()
        findNavController().navigate(refresh!!)
        Log.e("Access","$refresh")
    }

    fun showLangDialog(){
        val langList = arrayOf(getString(R.string.arabic),getString(R.string.english))
        val lBuilder = AlertDialog.Builder(context)
        lBuilder.setTitle(getString(R.string.Choose_language))
        lBuilder.setPositiveButton(getString(R.string.ok)){ _, _ ->
            refreshCurrentFragment()
        }
        lBuilder.setSingleChoiceItems(langList,-1){
            dialog, which ->
            if (which == 0){
                setLocale("ar")
            }else{
                setLocale("en")
            }
        }
        val lDialog = lBuilder.create()
        lDialog.show()
    }
    fun setLocale(lang: String){
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        requireContext().resources.updateConfiguration(config,requireContext().resources.displayMetrics)

        var sharedPreferences = requireContext().getSharedPreferences("share_pref", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("share", lang)
        editor.apply()
    }

}
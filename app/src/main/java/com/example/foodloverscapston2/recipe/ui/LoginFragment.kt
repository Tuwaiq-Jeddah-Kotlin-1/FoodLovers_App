package com.example.foodloverscapston2.recipe.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R
import com.example.foodloverscapston2.recipe.data.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {
    private lateinit var sing_up: TextView
    private lateinit var log_in: TextView
    private lateinit var sing_up_Layout: LinearLayout
    private lateinit var log_in_Layout: LinearLayout
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private  val email_pattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        auth = FirebaseAuth.getInstance()
        val email = view.findViewById<TextInputEditText>(R.id.login_email)
        val password = view.findViewById<TextInputEditText>(R.id.login_password)
        val singup_email = view.findViewById<TextInputEditText>(R.id.singup_email)
        val singup_passwords = view.findViewById<TextInputEditText>(R.id.singup_passwords)
        val singUp_password_conf = view.findViewById<TextInputEditText>(R.id.singUp_password_conf)
        val btn_logIn = view.findViewById<Button>(R.id.singIn)
        val btn_singUp = view.findViewById<Button>(R.id.sing_Up)
        val user_name = view.findViewById<TextInputEditText>(R.id.Name)
        val forgot = view.findViewById<TextView>(R.id.forgot)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.actionLoginFragmentToExplorerFragment)
        }

        btn_singUp.setOnClickListener {
            if (checkEmpty(arrayListOf(user_name, singup_email, singup_passwords))) {
                if (singUp_password_conf.text.toString() != singup_passwords.text.toString()) {
                    singUp_password_conf.error = getString(R.string.password_mismatch)
                } else {
                    auth.createUserWithEmailAndPassword(
                        singup_email.text.toString(),
                        singup_passwords.text.toString()
                    )
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                val firebaseUserId = auth.currentUser!!.uid!!
                                reference = FirebaseDatabase.getInstance().reference.child("Users")
                                    .child(firebaseUserId)
                                val user = User(
                                    firebaseUserId,
                                    singup_email.text.toString(),
                                    user_name.text.toString()
                                )
                                firebaseFirestore = FirebaseFirestore.getInstance()
                                firebaseFirestore.collection("users").document(firebaseUserId)
                                    .set(user)
                                    .addOnSuccessListener {
                                        Log.d("TAG", "DocumentSnapshot successfully written!")
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("TAG", "Error writing document", e)
                                    }
                                Toast.makeText(
                                    context,
                                    getString(R.string.you_have_created_new_account_successfully),
                                    Toast.LENGTH_LONG
                                )
                                    .show()

                                findNavController().navigate(R.id.actionLoginFragmentToExplorerFragment)

                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                }
            }
        }

        btn_logIn.setOnClickListener {
            if (checkEmpty(arrayListOf(email, password))) {

                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                context,
                                getString(R.string.login_is_successfully),
                                Toast.LENGTH_LONG
                            )
                                .show()
                            findNavController().navigate(R.id.actionLoginFragmentToExplorerFragment)
                        } else {
                            Toast.makeText(
                                context,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            }
        }

        forgot.setOnClickListener {

            findNavController().navigate(R.id.actionLoginFragmentToForgotPassWordFragment)
        }

        sing_up = view.findViewById(R.id.singUp)
        log_in = view.findViewById(R.id.logIn)
        sing_up_Layout = view.findViewById(R.id.singUpLayout)
        log_in_Layout = view.findViewById(R.id.logInLayout)

        sing_up.setOnClickListener {
            sing_up.background = resources.getDrawable(R.drawable.switch_trcks, null)
            sing_up.setTextColor(resources.getColor(R.color.textColor, null))
            log_in.background = null
            sing_up_Layout.visibility = View.VISIBLE
            log_in_Layout.visibility = View.GONE
            log_in.setTextColor(resources.getColor(R.color.darkB, null))
        }
        log_in.setOnClickListener {
            sing_up.background = null
            sing_up.setTextColor(resources.getColor(R.color.darkB, null))
            log_in.background = resources.getDrawable(R.drawable.switch_trcks, null)
            sing_up_Layout.visibility = View.GONE
            log_in_Layout.visibility = View.VISIBLE
            log_in.setTextColor(resources.getColor(R.color.textColor, null))
        }

        return view
    }

    private fun checkEmpty(arrayListOf: ArrayList<EditText>): Boolean {
        var returnValue = false
        for (i in arrayListOf) {
            if (i.text.toString() == ""){
                i.error = getString(R.string.must_be_filled)
                returnValue = false
            } else {
                returnValue = true
            }
        }
        return returnValue
    }

    fun email(singup_email:String):Boolean{

        if(singup_email.matches(email_pattern.toRegex()))

          return true
          return false
    }
}


package com.example.foodloverscapston2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodloverscapston2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class ForgotPassWordFragment : Fragment() {

    private  lateinit var submitButton: Button
    private lateinit var forgotEmailAddress :EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_forgot_pass_word, container, false)

        submitButton = view.findViewById(R.id.submitButton)
        forgotEmailAddress = view.findViewById(R.id.forgotEmailAddress)

        submitButton.setOnClickListener{

            val fEmail : String = forgotEmailAddress.text.toString().trim(){ it <= ' '}
            if (fEmail.isEmpty()){
                Toast.makeText(context,
                    "Please enter email address",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(fEmail)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(context,
                                "Email sent successfully to reset your password",
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.actionForgotPassWordFragmentToLoginFragment)
                        }
                    }
            }
        }

        return view
    }

}
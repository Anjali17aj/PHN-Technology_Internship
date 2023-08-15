package com.example.loginpage

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.loginpage.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var myAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAuth = FirebaseAuth.getInstance() //creating firebase instance

        //set click actions
        // TODO: Understand how can you clear the error
        binding.btnLogin.setOnClickListener {
            //get values from ets
            val username = binding.tietUsername.text.toString()
            val password = binding.tietPassword.text.toString()


            if (username.isBlank()) { //"", " ","     "
//show an error : "please enter a valid username"
                binding.etUsername.error = "Please enter a valid username"
            }
            if (password.isEmpty()) {
//show error
                binding.etPassword.error = "Please enter a password first"
            }

            //check if user exists
            //if exists goto next screen
            // if not then create user with mail id and pass

//if everything is fine, then submit

            if (checkFormDetails(username, password)) {
                myAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener { signInTask ->
                        if (signInTask.isSuccessful) {
                            Log.d("RandomLogTagForMyApp", "Sign in Successful")
                        } else {
                            Log.d("RandomLogTagForMyApp", "${signInTask.exception}")
                            // Add a check
                            // if (it.exception is of the type FirebaseAuthInvalidUserException) {
                            // Create a new user
                            myAuth.createUserWithEmailAndPassword(username, password)
                                .addOnCompleteListener { signUpTask ->
                                    if (signUpTask.isSuccessful) {
                                        Log.d("RandomLogTagForMyApp", "SignUp Successful")
                                    } else {
                                        Log.d("RandomLogTagForMyApp", "${signUpTask.exception}")
                                    }
                                }
                        }
                    }

                // Create a new user
                // Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_SHORT).show()

                // TODO: Goto the next activity
                //  val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
//                intent.putExtra(USERNAME_KEY, username)
//                intent.putExtra(PASSWORD_KEY, password)
//               val bundle = Bundle()
//                bundle.putString(USERNAME_KEY, username)
//                bundle.putString(PASSWORD_KEY, password)
//               intent.putExtras(bundle)
//
//               startActivity(intent)

            } else {
                Log.d("RandomLogTagForMyApp", "Code in else block")
            }
        }
        binding.tvGotoFAQs.setOnClickListener{
            //redirect this to a web page www.google.com   - this is receiver and sender is main activity
            //www.google.com -> convert this url into uri
            // in android when we have to go to anything we use uniform resource identifier
            val url = "https://www.google.com"
            val uri = Uri.parse(url) //converting url to uri using parse
            //this is how we log things:-
            Log.d("LoginCustomTag1", url)
            Log.d("LoginCustomTag2", uri.toString())
            println("URL - $url /nURI - $uri")
            val implicitIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(implicitIntent)
          }
}

override fun onStart() {
    super.onStart()
}

override fun onResume() {
    super.onResume()
}

override fun onPause() {
    super.onPause()
}

override fun onStop() {
    super.onStop()
}

override fun onDestroy() {

    super.onDestroy()
}

private fun checkFormDetails(username: String, password: String): Boolean {
    return !(username.isBlank() || password.isEmpty())

}
}


package com.example.loginpage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.loginpage.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding  //create binding variable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater) //get binding variable a definition that we need all the views inflated from activity welcome binding xml file to binding object.
        setContentView(binding.root) //set content activity to binding root that is parent

        //val tvWelcome: TextView = findViewById(R.id.tvWelcome)

        // Fetch the name from MainActivity and  display it here
        // val username = intent.getStringExtra("USERNAME_KEY")
       // val password = intent.getStringExtra("PASSWORD_KEY")
        val username = intent.extras?.getString(USERNAME_KEY) //fetching bundle from main activity
        val password = intent.extras?.getString(PASSWORD_KEY)

        //tvWelcome.text = "Welcome $username \n$password"
        binding.tvWelcome.text = "Welcome $username \n$password"  //access each element of xml file using binding variable.

        //SharedPreferences and How to put our data in shared prefs
        val contactNum = "9283574500"
        val isAdmin = true
        val ageOfUser = 22

        val sharedPrefs = getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE) //get shared preference file which is already there if it's not there then it will create one and open it in private mode
        //Editor used to edit values in shared preferences.
        val editor = sharedPrefs.edit()

        editor.putString(SP_CONTACT_NUMBER_KEY, contactNum) //in editor we are putting string and these are stored in shared preferences file.
        editor.putBoolean(SP_IS_ADMIN_KEY, isAdmin)
        editor.putInt(SP_AGE_KEY, ageOfUser)

        editor.apply()  // pushes the values in an async manner
//        editor.commit()  // pushes the values asap (in a sync manner)


        // How to access the data from sharedPrefs
        // Assume that you are on an entirely different activity
        val sharedPreferences = getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)

        val cNum = sharedPreferences.getString(SP_CONTACT_NUMBER_KEY, "No Number Found")
        val isAdm = sharedPreferences.getBoolean(SP_IS_ADMIN_KEY, false)
        val age = sharedPreferences.getInt(SP_AGE_KEY, 0)
        val name = sharedPreferences.getString("nameKey", "No name found")

        Log.d("SharedPreferenceTestTag", "$cNum, $isAdm, $age, $name")
    }
}
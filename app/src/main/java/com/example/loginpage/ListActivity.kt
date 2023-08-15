package com.example.loginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.databinding.ActivityListBinding


class ListActivity : AppCompatActivity(), MyItemClickListener { //MyItemClickListener implementing this interface in list activity. android ask to override fun onItemClicked method (line 84) then it is passed to adapter

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val integers = (1..25).toList()
//
//        val myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, integers)
//        binding.myListView.adapter = myAdapter

        // This is a click event on the entire list
//        binding.myListView.setOnClickListener {
//            Log.d("lqiuefquew", "Item Clicked")
//        }

        // We need a click event for one particular element of the list
//        binding.myListView.setOnItemClickListener { parent, view, position, id ->
//            Log.d("udyxuc", "Element $position was clicked")
//        }

        val users = listOf(
            User("Rohit", "Saini", "123"),
            User("Niranjan", "V", "234"),
            User("Aayan", "Shukla", "345"),
            User("Pratyansh", "M", "456"),
            User("Prem", "Kumar", "567"),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
            User(),
        )

        // Setting a Layout Manager for the RV
        binding.myRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Setting an Adapter for the RV
        binding.myRecyclerView.adapter = MyAdapter(users, this) //this will call below methods
    }

    override fun onItemClicked(user: User) { //call back of the interface from recycle view utils.kt
        Log.d("agdwiwu", user.toString()) //getting the method invoked
    }

    override fun onProfilePicClicked(user: User) { //call back of the interface from recycle view utils.kt
        Log.d("kdebfoine", "View Profile Pic") //getting the method invoked
    }
}

data class User(
    val firstname: String = "ABC",
    val lastname: String = "MNO",
    val contact: String = "XYZ"
)
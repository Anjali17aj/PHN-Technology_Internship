package com.example.loginpage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

// Step 1: ViewHolder -- For holding the custom Layout file for our List Item  - holder is not a view it is holding everything together
//view holder class
class MyViewHolder(itemView: View) : ViewHolder(itemView) {
    private val ivProfilePic : ImageView = itemView.findViewById(R.id.ivUserPic)
    private val tvFirstName: TextView = itemView.findViewById(R.id.tvFirstName)
    private val tvLastName: TextView = itemView.findViewById(R.id.tvLastName)
    private val tvContactNum: TextView = itemView.findViewById(R.id.tvContact)

    fun bindData(user: User, listener: MyItemClickListener) {
        // ivProfilePic.setImage("Some Image")
        tvFirstName.text = user.firstname
        tvLastName.text = user.lastname
        tvContactNum.text = user.contact

        ivProfilePic.setOnClickListener {
            listener.onProfilePicClicked(user) //starting the invokation here and getting the invokation in list activity.kt
        }
        itemView.setOnClickListener {          //invoke this callback whenever item view is clicked  (setOnClickListener ) the item view is my_list_item
//            listener.onItemClicked(user)             //when entire item view is clicked using listener interface we are invoking onItemClickedas soon as we call this the action goes to listactivity.kt and override fun onItemClicked is triggered
            Log.d("wuyevfi", "iqefo")
            // Call an api
        }
    }
}

// Step 2: Adapter -- Connecting everything and providing it to the RecycleView
class MyAdapter(private val users: List<User>, private val listener: MyItemClickListener): Adapter<MyViewHolder>() { //passing user values and from adapter we are accepting as listener and then sending it to view holder (line 66)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // TODO: To attach the view and convert it into MyViewHolder
        val view = LayoutInflater
            .from(parent.context) //using context to inflate my layout
            .inflate(R.layout.my_list_item, parent, false) //list of users and inflating layout i.e converting xml file into object

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        // TODO: Return the size of the list that needs to be populated
        return users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = users[position]

        // Setting data to the View
        // But for this you need to call holder.view again and again
        // Also you need to expose the variables of your VH class which is not required at all
//        holder.tvFirstName.text = user.firstname
//        holder.tvLastName.text = user.lastname
//        holder.tvContactNum.text = user.contact

        // So to avoid all that we simply create a function in our VH and call it from our Adapter
        holder.bindData(user, listener) //using bindData to set values tvFirstName.text  ..so on hen sending it to view holder line 22
    }
}

// Interface -- For defining a `Click Action Event` on our List item
interface MyItemClickListener {   //creating an  interface
    fun onItemClicked(user: User) // method that will act as a call back
    fun onProfilePicClicked(user: User) //call back of the interface
}
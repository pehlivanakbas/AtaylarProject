package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.R

class AllUsersAdapter(private val userlist: MutableList<User>, var context: Context
) :
RecyclerView.Adapter<AllUsersAdapter.AllUserviewHolder>() {
    var onItemClick : ((User) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AllUserviewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.
            assignfragmentuserassignlist, parent, false)

        return AllUsersAdapter.AllUserviewHolder(view)
    }
    override fun onBindViewHolder(holder: AllUserviewHolder, position: Int) {
        val currentItem=userlist[position]
        val username: TextView = holder.itemView.findViewById(R.id.chooseassign)
        username.text = "${currentItem.name} ${currentItem.userRole}"
       // val  checkBox:CheckBox=holder.itemView.findViewById(R.id.checkBox)
       // checkBox.visibility=View.VISIBLE


        holder.itemView.setOnClickListener() {
            onItemClick?.invoke(currentItem)
}}

    override fun getItemCount(): Int {
        return userlist.size
    }

    class AllUserviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var employeecard: LinearLayout = itemView.findViewById(R.id.userlayout)
        }
    }

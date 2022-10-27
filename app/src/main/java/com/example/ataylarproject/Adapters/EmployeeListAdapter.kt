package com.example.ataylarproject.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Data.DataEmployee
import com.example.ataylarproject.Fragments.EmployeelistFragment
import com.example.ataylarproject.R
import com.google.android.material.imageview.ShapeableImageView

interface EmployeeListInterface {
    fun transactionDetailBTPressed(index: Int)
}
class EmployeeListAdapter (private val userlist: ArrayList<DataEmployee>,
                           private val mListener: EmployeelistFragment) :
    RecyclerView.Adapter<EmployeeListAdapter.EmployeeListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.
                list_items, parent, false)

        return EmployeeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        val currentItem=userlist[position]

        val companyname: TextView = holder.itemView.findViewById(R.id.companyname)
        companyname.text = currentItem.companyName
        val customername: TextView = holder.itemView.findViewById(R.id.customername)
        customername.text = currentItem.Employeename
        val companyImage: ImageView = holder.itemView.findViewById(R.id.company_image)
        companyImage.setImageResource(currentItem.companyImage)

        holder.employeecard .setOnClickListener {
            mListener.transactionDetailBTPressed(position)
        }
    }

    override fun getItemCount(): Int {
        return userlist.size
    }
    class EmployeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var employeecard: LinearLayout = itemView.findViewById(R.id.employee_card)
    }

}
package com.example.ataylarproject.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.EmployeelistFragment
import com.example.ataylarproject.Models.User
import com.example.ataylarproject.R
import okio.blackholeSink


class EmployeeListAdapter(private val userlist: MutableList<User>,
                          private val mListener: EmployeelistFragment
) :
    RecyclerView.Adapter<EmployeeListAdapter.EmployeeListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.
                list_items, parent, false)

        return EmployeeListViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        val currentItem=userlist[position]

        val name: TextView = holder.itemView.findViewById(R.id.name)
        name.text = "${currentItem.name}"
        val phoneNumberEditText: TextView = holder.itemView.findViewById(R.id.phoneNumberEditText)
        phoneNumberEditText.text = "${currentItem.phoneNumber}"
        val professionalChamber: TextView = holder.itemView.findViewById(R.id.professionalChamber)
        professionalChamber.text = "${currentItem.professionalChamber}"
        val tckimlik:TextView=holder.itemView.findViewById(R.id.tckimlik)
        tckimlik.text = "${currentItem.tcKimlikNo}"
        val tckimlikserino: TextView = holder.itemView.findViewById(R.id.tckimlikserino)
        tckimlikserino.text = "${currentItem.tcKimlikSeriNo}"
        val sskNo: TextView = holder.itemView.findViewById(R.id.sskNo)
        sskNo.text = "${currentItem.sskNo}"
        val registerno: TextView = holder.itemView.findViewById(R.id.registerno)
        registerno.text = "${currentItem.registerNo}"

        val listitem:LinearLayout=holder.itemView.findViewById(R.id.employee_card)
        if(position%2==0){
            listitem.setBackgroundColor(R.color.teal_200)
        }
        //val companyImage: ImageView = holder.itemView.findViewById(R.id.company_image)
       //companyImage.setImageResource(currentItem.name)


    }




    override fun getItemCount(): Int {
        return userlist.size
    }
    class EmployeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var employeecard: LinearLayout = itemView.findViewById(R.id.employee_card)
    }

}
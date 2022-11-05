package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.ProjectRegionFragment
import com.example.ataylarproject.R

interface RegionListInterface{
    fun transactionDetailBTPressed(index: Int)
}
class RegionAdapter(

    private val itemList: List<String>,
    var context: Context,
    private val mListener: ProjectRegionFragment
):
    RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {

     class RegionViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
         var textviewRegionName: TextView =itemView.findViewById(R.id.textregion)

     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
         val view: View =
             LayoutInflater.from(parent.context).inflate(R.layout.region_list_cardview, parent, false)
return RegionViewHolder(view)
     }

     override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
         holder.textviewRegionName.text=itemList[position]
         holder.textviewRegionName.setOnClickListener {
             mListener.transactionDetailBTPressed(position)
         }
     }

     override fun getItemCount(): Int {
    return itemList.size
     }


 }


package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.ProjectLocationFragment
import com.example.ataylarproject.Fragments.ProjectSitesFragment
import com.example.ataylarproject.R


interface LocationListInterface {
    fun transactionDetailBTPressed(index: Int)
}
 class LocationAdapter(
    private val itemList: List<String>,
    var context: Context,
    private val mListener: ProjectLocationFragment
    ):
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {


     class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var textlocation: AppCompatTextView = itemView.findViewById(R.id.textlocation)
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
         val view: View =
             LayoutInflater.from(parent.context).inflate(
                 R.layout.location_cardview, parent, false
             )
         return LocationViewHolder(view)
     }

     override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
         holder.textlocation.text=itemList[position]
         holder.textlocation.setOnClickListener {
             mListener.transactionDetailBTPressed(position)
         }
     }

     override fun getItemCount(): Int {
       return itemList.size
     }
 }


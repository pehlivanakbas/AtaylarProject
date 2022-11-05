package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.FaultsFragment
import com.example.ataylarproject.Fragments.ProjectLocationFragment
import com.example.ataylarproject.R


interface FaultListInterface {
    fun transactionbuttonFault(index: Int)
}
class FaultsAdapter(
    private val itemList: List<String>,
    var context: Context,
    private val mListener: FaultsFragment
):
    RecyclerView.Adapter<FaultsAdapter.FaultViewHolder>() {

    class FaultViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textFaults: AppCompatTextView = itemView.findViewById(R.id.textFault)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaultViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.fault_list_cardview, parent, false
            )
        return FaultViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaultViewHolder, position: Int) {
        holder.textFaults.text=itemList[position]
        holder.textFaults.setOnClickListener {
            mListener.transactionbuttonFault(position)
        }
    }

    override fun getItemCount(): Int {
     return itemList.size
    }
}
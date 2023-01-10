package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.FragmentMainLegistlation
import com.example.ataylarproject.Models.LegislationModel
import com.example.ataylarproject.R


interface LegislationListInterface {
    fun transactionDetailBTPressed(index: Int)
}

class LegislationAdapter(
    private val itemList: MutableList<LegislationModel>,
    var context: Context,
    private val mListener: FragmentMainLegistlation,


    ) : RecyclerView.Adapter<LegislationAdapter.LegislationViewHolder>() {


    class LegislationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var legislationtext: AppCompatTextView = itemView.findViewById(R.id.textlegislation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegislationViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_legistlation_list, parent, false)
        return LegislationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LegislationViewHolder, position: Int) {
        holder.legislationtext.text = itemList[position].text
        holder.legislationtext.setOnClickListener {
            mListener.transactionDetailBTPressed(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size

    }
}
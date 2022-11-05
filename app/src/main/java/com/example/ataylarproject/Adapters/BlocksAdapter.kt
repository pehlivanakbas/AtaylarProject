package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.ProjectBlocksFragment
import com.example.ataylarproject.R

interface BlockListInterface {
fun transactionbuttonRegion(position: Int)
}

class BlocksAdapter(
    private val itemList: List<String>,
    var contextblock: Context,
    private val mListenerblock:ProjectBlocksFragment
    ):

   RecyclerView.Adapter<BlocksAdapter.BlocksViewHolder>() {

    class BlocksViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var textViewblock: AppCompatTextView = itemView.findViewById(R.id.textblock)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlocksViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.
            project_list_cardview,parent, false)
        return BlocksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlocksViewHolder, position: Int) {

    holder.textViewblock.text=itemList[position]
    holder.textViewblock.setOnClickListener {
    mListenerblock.transactionbuttonRegion(position)

    }

    }
    override fun getItemCount(): Int {
        return itemList.size
    }}





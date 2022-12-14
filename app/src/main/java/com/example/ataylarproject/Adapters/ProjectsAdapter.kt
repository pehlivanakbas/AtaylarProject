package com.example.ataylarproject.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.Fragments.ProjectSitesFragment
import com.example.ataylarproject.R

interface SiteListInterface {
    fun transactionDetailBTPressed(index: Int)
    fun projectInfoAddBtPressed(index: Int)
}

class ProjectsAdapter(
    private val itemList: List<String>,
    var context: Context,
    private val mListener: ProjectSitesFragment,
):
    RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> (){

    class ProjectsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: AppCompatTextView = itemView.findViewById(R.id.text)
        var buttoninfosites: AppCompatButton = itemView.findViewById(R.id.siteinfobutton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.
            sitepage, parent, false)
        return ProjectsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.textView.text = itemList[position]
        holder.textView.setOnClickListener{
            mListener.transactionDetailBTPressed(position)
        }
        holder.buttoninfosites.setOnClickListener {
            mListener.projectInfoAddBtPressed(position)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size

    }
     }


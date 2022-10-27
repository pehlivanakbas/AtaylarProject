package com.example.ataylarproject.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ataylarproject.R

class ProjectsAdapter(private val itemList: List<String>): RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> (){

    class ProjectsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: AppCompatTextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.
            project_list_cardview, parent, false)


        return ProjectsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.textView.text = itemList[position]
    }

    override fun getItemCount() = itemList.size
}
package com.example.notes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.db.Notes
import com.example.notes.R
import com.example.notes.clickListener.ItemClickListener

class NotesAdapter(val list:List<Notes>, val itemClickListener: ItemClickListener):RecyclerView.Adapter<NotesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val notes=list[position]
        val Tittle=notes.tittle
        val Descripson=notes.description
        holder.textViewTittle.text=Tittle
        holder.textViewDescripson.text=Descripson
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                itemClickListener.onClick(notes)
            }

        })
        holder.checkboxMarkStatus.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                itemClickListener
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size

    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textViewTittle: TextView =itemView.findViewById(R.id.textViewTittle)
        val textViewDescripson: TextView =itemView.findViewById(R.id.textViewDescription)
        val checkboxMarkStatus: CheckBox =itemView.findViewById(R.id.checkboxMarkStatus)

    }

}
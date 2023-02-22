package com.example.phnapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.phnapplication.models.Data

class DetailsAdapter(): RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    lateinit var allData: ArrayList<Data>
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.name)
        var companyName = view.findViewById<TextView>(R.id.company)
        var title = view.findViewById<TextView>(R.id.title)
        var body = view.findViewById<TextView>(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.details_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        allData.let {
            val item = it[position]
            holder.apply {
                name.text = item.name
                companyName.text = item.companyName
                title.text = item.title
                body.text = item.body
            }
        }
    }

    override fun getItemCount(): Int {
        return allData.size?: 0
    }

    fun setData(tempData: ArrayList<Data>?){
        tempData?.let {
            this.allData = it
            notifyDataSetChanged()
        }
    }
}
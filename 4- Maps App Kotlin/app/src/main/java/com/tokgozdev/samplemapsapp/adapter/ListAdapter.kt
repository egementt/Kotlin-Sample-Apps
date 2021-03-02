package com.tokgozdev.samplemapsapp.adapter

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tokgozdev.samplemapsapp.R


class ListAdapter(var context: Context, var list: ArrayList<com.tokgozdev.samplemapsapp.model.Location>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false)

        var name = convertView.findViewById<TextView>(R.id.itemNameText)
        var country = convertView.findViewById<TextView>(R.id.itemCountryText)
        var type = convertView.findViewById<TextView>(R.id.itemTypeText)

        name.text = list[position].name
        country.text = "Country: ${list[position].country}"
        type.text = "Type: ${list[position].type}"

        return convertView


    }


}







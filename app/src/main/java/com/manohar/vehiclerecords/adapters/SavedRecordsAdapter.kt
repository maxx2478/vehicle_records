package com.manohar.vehiclerecords.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.model.VehicleModel
import java.util.*
import kotlin.collections.ArrayList

class SavedRecordsAdapter : RecyclerView.Adapter<SavedRecordsViewHolder>
{

    var array:ArrayList<VehicleModel>?=null
    var context: Context?=null
    var  view:View?=null

    constructor(array: ArrayList<VehicleModel>, context: Context, view:View)
    {
        this.array = array
        array.reverse()
        this.context = context
        this.view = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRecordsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_saved, parent, false)
        return SavedRecordsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedRecordsViewHolder, position: Int) {

        val vehicleModel = array?.get(position)
        holder.regno.text = vehicleModel!!.regNo.toUpperCase(Locale.ROOT)
        holder.model.text = vehicleModel.modelname.toUpperCase(Locale.ROOT)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("regno", vehicleModel.regNo)
            bundle.putString("make", vehicleModel.make)
            bundle.putString("class", vehicleModel.classtype)
            bundle.putString("model", vehicleModel.modelname)
            bundle.putString("fueltype", vehicleModel.fueltype)
            bundle.putString("transtype", vehicleModel.transmissiontype)
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_vehicleDetailFragment, bundle)

        }

    }

    override fun getItemCount(): Int {
        return  array!!.size
    }

}

class SavedRecordsViewHolder(view: View): RecyclerView.ViewHolder(view)
{

    val regno:TextView = view.findViewById(R.id.regno)
    val model:TextView = view.findViewById(R.id.model)

}
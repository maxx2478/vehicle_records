package com.manohar.vehiclerecords.adapters

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.model.VehicleModel
import com.manohar.vehiclerecords.utils.RoomDB
import com.manohar.vehiclerecords.utils.SessionManager
import com.manohar.vehiclerecords.views.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TransmissionAdapter : RecyclerView.Adapter<TransmissionViewHolder>
{

    var array:ArrayList<String>?=null
    var context:Context?=null
    var activity:String?=null
    var view:View?=null
    private var sessionManager: SharedPreferences?=null
    lateinit var roomDB: RoomDB
    var fragmentx:Fragment?=null

    constructor(array: ArrayList<String>, context: Context, activity: String, view: View, fragment:Fragment)
    {
        this.array = array
        this.context = context
        this.activity = activity
        this.view = view
        this.fragmentx = fragment
        sessionManager = SessionManager.customPrefs(context, "session")
        roomDB = Room.databaseBuilder(context, RoomDB::class.java, "my_records").build()

    }

    fun updateData(array: ArrayList<String>)
    {
        this.array = array
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransmissionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_vehicledata, parent, false)
        return TransmissionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransmissionViewHolder, position: Int) {

        holder.title.text = array?.get(position)


            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val bundle = Bundle()
                    bundle.putString("regno", sessionManager!!.getString("regno", " "))
                    bundle.putString("class", sessionManager!!.getString("class", " "))
                    bundle.putString("make", sessionManager!!.getString("make", " "))
                    bundle.putString("model", sessionManager!!.getString("model", " "))
                    bundle.putString("fueltype", sessionManager!!.getString("fueltype", " "))
                    bundle.putString("transtype", array?.get(position))

                    val vehicleModel = VehicleModel(
                            sessionManager!!.getString("regno", " ")!!,
                            sessionManager!!.getString("class", " ")!!,
                            sessionManager!!.getString("make", " ")!!,
                            sessionManager!!.getString("model", " ")!!,
                            sessionManager!!.getString("fueltype", " ")!!,
                            array?.get(position)!!
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        roomDB.vehicleDao().saveVehicle(vehicleModel)
                        withContext(Dispatchers.Main)
                        {
                            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                        }
                    }



                    fragmentx!!.requireActivity().finish()
                    fragmentx!!.requireActivity().overridePendingTransition( 0, 0);
                    fragmentx!!.requireActivity().startActivity(Intent(fragmentx!!.activity, MainActivity::class.java));
                    fragmentx!!.requireActivity().overridePendingTransition( 0, 0);

                }

            })




    }

    override fun getItemCount(): Int {
        return  array!!.size
    }

}

class TransmissionViewHolder(view: View): RecyclerView.ViewHolder(view)
{
    val title: TextView = view.findViewById(R.id.title)

}
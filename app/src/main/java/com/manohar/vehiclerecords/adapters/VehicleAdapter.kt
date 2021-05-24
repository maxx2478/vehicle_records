package com.manohar.vehiclerecords.adapters

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.model.VehicleModel
import com.manohar.vehiclerecords.utils.RoomDB
import com.manohar.vehiclerecords.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class VehicleAdapter : RecyclerView.Adapter<vehicleViewHolder>
{

    var array:ArrayList<String>?=null
    var context:Context?=null
    var activity:String?=null
    var view:View?=null
    private var sessionManager: SharedPreferences?=null
    lateinit var roomDB: RoomDB

    constructor(array: ArrayList<String>, context: Context, activity: String, view: View)
    {
        this.array = array
        this.context = context
        this.activity = activity
        this.view = view
        sessionManager = SessionManager.customPrefs(context, "session")
        roomDB = Room.databaseBuilder(context, RoomDB::class.java, "my_records").build()

    }

    fun updateData(array: ArrayList<String>)
    {
        this.array = array
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_vehicledata, parent, false)
        return vehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: vehicleViewHolder, position: Int) {

        holder.title.text = array?.get(position)

       if (activity.equals("make", ignoreCase = true))
        {
            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("regno", sessionManager!!.getString("regno", " "))
                bundle.putString("class", sessionManager!!.getString("class", " "))
                sessionManager!!.edit().putString("make", array?.get(position))
                    .apply() // saving make to SP
                bundle.putString("make", array?.get(position))

                it.findNavController().navigate(R.id.action_makeBrandFragment_to_modelFragment, bundle)


            }
        }


        else if (activity.equals("model", ignoreCase = true))
        {
            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val bundle = Bundle()
                    bundle.putString("regno", sessionManager!!.getString("regno", " "))
                    bundle.putString("class", sessionManager!!.getString("class", " "))
                    bundle.putString("make", sessionManager!!.getString("make", " "))
                    bundle.putString("model", array?.get(position))
                    sessionManager!!.edit().putString("model", array?.get(position)).apply() // saving model to SP

                    view?.post {
                        Navigation.findNavController(view!!).navigate(R.id.action_modelFragment_to_fueltypeFragment, bundle)

                    }

                }

            })
        }
        else if (activity.equals("fueltype", ignoreCase = true))
        {
            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {

                    val bundle = Bundle()
                    bundle.putString("regno", sessionManager!!.getString("regno", " "))
                    bundle.putString("class", sessionManager!!.getString("class", " "))
                    bundle.putString("make", sessionManager!!.getString("make", " "))
                    bundle.putString("model", sessionManager!!.getString("model", " "))
                    bundle.putString("fueltype", array?.get(position))
                    sessionManager!!.edit().putString("fueltype", array?.get(position)).apply() // saving fueltype to SP

                    Navigation.findNavController(view!!).navigate(R.id.action_fueltypeFragment_to_transmissiontypesFragment, bundle)
                }

            })
        }
        else if (activity.equals("transtype", ignoreCase = true))
        {
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

                            val controller = Navigation.findNavController(view!!)
                            controller.popBackStack(R.id.transmissiontypesFragment, true);
                            controller.navigate(R.id.homeScreenFragment, null)

                }

            })
        }



    }

    override fun getItemCount(): Int {
        return  array!!.size
    }

}

class vehicleViewHolder(view: View): RecyclerView.ViewHolder(view)
{
    val title: TextView = view.findViewById(R.id.title)

}
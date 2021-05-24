package com.manohar.vehiclerecords.views.vehiclerecordFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.adapters.SavedRecordsAdapter
import com.manohar.vehiclerecords.model.VehicleModel
import com.manohar.vehiclerecords.utils.RoomDB
import com.manohar.vehiclerecords.views.MainActivity
import kotlinx.coroutines.*

class HomeScreenFragment : Fragment()
{

    var root:View?=null
    var recyclerView:RecyclerView?=null
    var progressbar:ProgressBar?=null
    var floatingactionbutton:FloatingActionButton?=null

    lateinit var savedRecordsAdapter: SavedRecordsAdapter
    lateinit var roomDB: RoomDB
    var error: TextView?= null
    var title: TextView?= null

    var array:ArrayList<VehicleModel>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        root= inflater.inflate(R.layout.fragment_home_screen, container, false)
        initializeViews(root)
        recyclerView!!.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )


        setonClickListeners()

        return root

    }

    private fun setonClickListeners() {
        floatingactionbutton!!.setOnClickListener {
            Navigation.findNavController(root!!).navigate(R.id.action_homeScreenFragment_to_regNumberFragment2)
        }

    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun initializeViews(root: View?) {
        recyclerView = root!!.findViewById(R.id.recyclerview)
        array = ArrayList()
        roomDB = Room.databaseBuilder(requireContext(), RoomDB::class.java, "my_records").build()
        floatingactionbutton = root.findViewById(R.id.additem)
        progressbar = root.findViewById(R.id.progressbar)
        error = root.findViewById(R.id.error)
        title = root.findViewById(R.id.headertitle)
        title!!.text = "Vehicle Records"

    }

    private fun getData()
    {
        CoroutineScope(Dispatchers.IO).launch {

            array = ArrayList<VehicleModel>(roomDB.vehicleDao().getAllRecords())

            withContext(Dispatchers.Main)
            {

                if (array!!.size==0)
                {
                    progressbar!!.visibility = View.GONE
                    error!!.visibility = View.VISIBLE
                }
                else if (array!!.size>0)
                {
                    progressbar!!.visibility = View.GONE
                    error!!.visibility = View.GONE

                }
                savedRecordsAdapter = SavedRecordsAdapter(array!!, requireContext(), root!!)
                recyclerView!!.adapter = savedRecordsAdapter

            }


        }
    }




}
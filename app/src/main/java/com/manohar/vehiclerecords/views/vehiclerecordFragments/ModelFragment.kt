package com.manohar.vehiclerecords.views.vehiclerecordFragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.adapters.VehicleAdapter
import com.manohar.vehiclerecords.network.ApiService
import com.manohar.vehiclerecords.utils.SessionManager
import com.manohar.vehiclerecords.views.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelFragment : Fragment() {

    var recyclerView: RecyclerView?=null
    var root: View?=null
    var apiService: ApiService?=null
    var classx:String?=null
    var regno:String?=null
    var make:String?=null
    var title: TextView?= null
    var backbutton: ImageView?= null
    private var sessionManager: SharedPreferences?=null
    lateinit var vehicleAdapter: VehicleAdapter
    private var contextx: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextx = context
    }

    override fun onDetach() {
        super.onDetach()
        contextx = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_model, container, false)

        initializeViews(root)
        vehicleAdapter = VehicleAdapter(arrayListOf(), requireContext(), "model", root!!)
        recyclerView!!.adapter = vehicleAdapter
        return  root

    }

    override fun onResume() {
        super.onResume()
        if (contextx!=null)
        {
            getdata()
        }
    }


    private fun getdata() {
        val service = apiService!!.getApiInstance()
        val call = service.getVehicleModels(classx, make)
        call.enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
                if (response.isSuccessful)
                    vehicleAdapter.updateData(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
            }


        })
    }


    private fun initializeViews(root: View?) {
        recyclerView = root!!.findViewById(R.id.modelbrandrv)
        recyclerView!!.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        title = root.findViewById(R.id.headertitle)
        backbutton = root.findViewById(R.id.back)
        title!!.text = "Choose Model"

        backbutton!!.setOnClickListener { requireActivity().onBackPressed() }

        apiService = ApiService()
        sessionManager = SessionManager.customPrefs(requireContext(), "session")
        regno = requireArguments().getString("regno")

        classx = requireArguments().getString("class")
        make = requireArguments().getString("make")

        sessionManager!!.edit().putString("regno", regno).apply()
        sessionManager!!.edit().putString("class", classx).apply()
        sessionManager!!.edit().putString("make", make).apply()

    }


}
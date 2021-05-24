package com.manohar.vehiclerecords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout


class VehicleDetailFragment : Fragment() {

    var makex:TextView?=null
    var modelx:TextView?=null
    var fueltypex:TextView?=null
    var transtypesx:TextView?=null
    var maintitle:TextView?=null
    var back:ImageView?=null

    var regnox:TextView?=null
    var root:View?=null
    var image: ImageView?=null

    var classx:String?=null
    var regno:String?=null
    var make:String?=null
    var model:String?=null
    var fueltype:String?=null
    var transtype:String?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_vehicle_detail, container, false)

        initializeViews(root)


        return root
    }

    private fun setData() {

        if (classx.equals("2w", ignoreCase = true))
        {
            image!!.setBackgroundResource(R.drawable.bike)
        }
        else if (classx.equals("4w", ignoreCase = true))
        {
            image!!.setBackgroundResource(R.drawable.car)
        }

        makex!!.text = make
        modelx!!.text = model
        fueltypex!!.text = fueltype
        transtypesx!!.text = transtype
        regnox!!.text = regno
        maintitle!!.text = "$model $fueltype"


    }

    private fun initializeViews(root: View?) {

        makex = root!!.findViewById(R.id.make)
        modelx = root.findViewById(R.id.model)
        fueltypex = root.findViewById(R.id.fueltype)
        transtypesx = root.findViewById(R.id.transtype)
        image = root.findViewById(R.id.imageview)
        regnox = root.findViewById(R.id.regno)
        maintitle = root.findViewById(R.id.headertitle)
        back = root.findViewById(R.id.back)
        back!!.setOnClickListener { requireActivity().onBackPressed() }
        regno = requireArguments().getString("regno")
        classx = requireArguments().getString("class")
        make = requireArguments().getString("make")
        model = requireArguments().getString("model")
        fueltype = requireArguments().getString("fueltype")
        transtype = requireArguments().getString("transtype")


        setData()
    }

}
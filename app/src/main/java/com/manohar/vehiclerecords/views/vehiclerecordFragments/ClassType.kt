package com.manohar.vehiclerecords.views.vehiclerecordFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.views.MainActivity

class ClassType : Fragment() {


    var twowheelerx:TextView?=null
    var fourwheelx:TextView?=null
    var root:View?=null
    var regno:String?=null
    var title: TextView?= null
    var backbutton: ImageView?= null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_class_type, container, false)

        getData()
        initializeViews(root)
        setonClickListeners()

        return root
    }

    private fun getData() {
        regno = requireArguments().getString("regno")

    }

    private fun setonClickListeners() {
        twowheelerx!!.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("regno", regno)
            bundle.putString("class", "2w")
            Navigation.findNavController(root!!).navigate(R.id.action_classType_to_makeBrandFragment, bundle)
        }

        fourwheelx!!.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("regno", regno)
            bundle.putString("class", "4w")
            Navigation.findNavController(root!!).navigate(R.id.action_classType_to_makeBrandFragment, bundle)
        }

        backbutton!!.setOnClickListener { requireActivity().onBackPressed() }

    }

    private fun initializeViews(root: View?) {
           twowheelerx = root!!.findViewById(R.id.twowheelers)
           fourwheelx = root.findViewById(R.id.fourwheeler)
           title = root.findViewById(R.id.headertitle)
           backbutton = root.findViewById(R.id.back)
           title!!.text = "Choose Class"

    }


}
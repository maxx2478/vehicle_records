package com.manohar.vehiclerecords.views.vehiclerecordFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.manohar.vehiclerecords.R
import com.manohar.vehiclerecords.views.MainActivity

class RegNumberFragment : Fragment() {

    var regno:EditText?=null
    var submit:Button?=null
    var regnostring:String?=null
    var root:View?=null
    var title: TextView?= null
    var backbutton: ImageView?= null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_reg_number, container, false)

        initializeViews(root)
        setOnClickListeners()



        return root
    }

    private fun setOnClickListeners() {
        submit!!.setOnClickListener {

            regnostring = regno!!.text.toString()
            if (regnostring!!.length>=6)
            {

                val bundle = Bundle()
                bundle.putString("regno", regnostring)
                Navigation.findNavController(root!!).navigate(R.id.action_regNumberFragment_to_classType, bundle)
            }
            else
            {
                Toast.makeText(requireContext(), "Invalid registration number entered", Toast.LENGTH_SHORT).show()
            }

        }

        backbutton!!.setOnClickListener { requireActivity().onBackPressed() }

    }

    private fun initializeViews(root: View?) {
        regno = root!!.findViewById(R.id.regno)
        submit = root.findViewById(R.id.submit)
        title = root.findViewById(R.id.headertitle)
        backbutton = root.findViewById(R.id.back)
        title!!.text = "Register your vehicle"
    }


}
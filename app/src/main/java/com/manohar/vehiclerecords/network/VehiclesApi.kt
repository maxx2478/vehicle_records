package com.manohar.vehiclerecords.network

import retrofit2.Call
import retrofit2.http.*

interface VehiclesApi {

    //to get 2w or 4w auto mobiles
    @GET("makes?")
    fun getVehicleClass(@Query("class") searchTerm: CharSequence?):Call<ArrayList<String>>


    //to get 2w or 4w auto mobiles with maker name
    @GET("models?")
    fun getVehicleModels(@Query("class") searchTerm: CharSequence?, @Query("make") makername:CharSequence?):Call<ArrayList<String>>


}
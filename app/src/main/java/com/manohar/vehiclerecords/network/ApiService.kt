package com.manohar.vehiclerecords.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiService
{
    private val BASE_URL = "https://test.turbocare.app/turbo/care/v1/"
    private val api:VehiclesApi

    init {

        val gson = GsonBuilder()
            .setLenient()
            .create()


        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(VehiclesApi::class.java)
    }

    fun getApiInstance(): VehiclesApi
    {
        return  api
    }




}
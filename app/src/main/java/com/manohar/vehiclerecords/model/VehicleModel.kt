package com.manohar.vehiclerecords.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VehicleModel (

    @PrimaryKey
    val regNo:String="",

    @ColumnInfo(name = "classtype")
    val classtype:String="",

    @ColumnInfo(name = "make")
    val make:String="",

    @ColumnInfo(name = "modelname")
    val modelname:String="",

    @ColumnInfo(name = "fueltype")
    val fueltype:String="",

    @ColumnInfo(name = "transtype")
    val transmissiontype:String=""

    )
{


}
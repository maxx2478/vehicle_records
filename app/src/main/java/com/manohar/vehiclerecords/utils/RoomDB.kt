package com.manohar.vehiclerecords.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manohar.vehiclerecords.model.VehicleModel

@Database(entities = [(VehicleModel::class)],version = 1, exportSchema = false)

abstract class RoomDB:RoomDatabase()
{

    abstract fun vehicleDao() : VehicleDao

}
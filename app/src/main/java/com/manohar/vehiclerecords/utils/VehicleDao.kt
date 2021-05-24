package com.manohar.vehiclerecords.utils

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manohar.vehiclerecords.model.VehicleModel

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVehicle(vehicleModel: VehicleModel)


    @Query(value = "Select * from VehicleModel")
    fun getAllRecords() : List<VehicleModel>

}
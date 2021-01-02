package it.alberto.wodapp.Wod.BaseWod.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BaseWodDao {

    @Query("SELECT * FROM base_wod_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BaseWod>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(baseWod: BaseWod)

    @Query("DELETE FROM base_wod_table")
    suspend fun deleteAll()

}
package it.alberto.wodapp.Wod.UserWod.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserWodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserWod(userWod: UserWod)

    @Update
    suspend fun updateUserWod(userWod: UserWod)

    @Query("SELECT * FROM user_wod_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserWod>>

    @Delete
    suspend fun deleteUserWod(userWod: UserWod)

    @Query("DELETE FROM user_wod_table")
    suspend fun deleteAllUserWod()

    @Query("SELECT * FROM user_wod_table WHERE date LIKE :date")
    fun findWodWithDateClicked(date: String): LiveData<List<UserWod>>
}
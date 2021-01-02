package it.alberto.wodapp.Wod.UserWod.repository

import androidx.lifecycle.LiveData
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import it.alberto.wodapp.Wod.UserWod.data.UserWodDao


class UserWodRepository(private val userWodDao: UserWodDao){

    //var date = "2/6/2020"

    suspend fun addUserWod(userWod: UserWod){
        userWodDao.addUserWod(userWod)
    }

    val readAllData: LiveData<List<UserWod>> = userWodDao.readAllData()

    suspend fun updateUserWod(userWod: UserWod){
        userWodDao.updateUserWod(userWod)
    }

    suspend fun deleteUserWod(userWod: UserWod){
        userWodDao.deleteUserWod(userWod)
    }

    suspend fun deleteAllUserWod(){
        userWodDao.deleteAllUserWod()
    }

    //val findWodWithDayClicked: LiveData<List<UserWod>> = userWodDao.findWodWithDateClicked(date)
}
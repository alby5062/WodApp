package it.alberto.wodapp.Wod.BaseWod.repository

import androidx.lifecycle.LiveData
import it.alberto.wodapp.Wod.BaseWod.data.BaseWod
import it.alberto.wodapp.Wod.BaseWod.data.BaseWodDao
import it.alberto.wodapp.Wod.UserWod.data.UserWodDao

class BaseWodRepository(private val baseWodDao: BaseWodDao) {

    val readAllData: LiveData<List<BaseWod>> = baseWodDao.readAllData()

    suspend fun insert(baseWod: BaseWod){
        baseWodDao.insert(baseWod)
    }

    suspend fun deleteAll(){
        baseWodDao.deleteAll()
    }
}
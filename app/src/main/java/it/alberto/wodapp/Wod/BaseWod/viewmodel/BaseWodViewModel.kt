package it.alberto.wodapp.Wod.BaseWod.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import it.alberto.wodapp.Database.WodRoomDatabase
import it.alberto.wodapp.Wod.BaseWod.data.BaseWod
import it.alberto.wodapp.Wod.BaseWod.repository.BaseWodRepository
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class BaseWodViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<BaseWod>>
    private val repository: BaseWodRepository

    init {
        val baseWodDao = WodRoomDatabase.getDatabase(application).baseWodDao()
        repository = BaseWodRepository(baseWodDao)
        readAllData = repository.readAllData
    }

    fun insert(baseWod: BaseWod){
        viewModelScope.launch (Dispatchers.IO) {
            repository.insert(baseWod)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}
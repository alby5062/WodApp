package it.alberto.wodapp.Wod.UserWod.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import it.alberto.wodapp.Database.WodRoomDatabase
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import it.alberto.wodapp.Wod.UserWod.repository.UserWodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class UserWodViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<UserWod>>
   // val findWodWithDateClicked: LiveData<List<UserWod>>
    private val repository: UserWodRepository

    init {
        val userWodDao = WodRoomDatabase.getDatabase(application).userWodDao()
        repository = UserWodRepository(userWodDao)
        readAllData = repository.readAllData
        //findWodWithDateClicked = repository.findWodWithDayClicked
    }

    fun addUserWod(userWod: UserWod){
        viewModelScope.launch (Dispatchers.IO) {
            repository.addUserWod(userWod)
        }
    }

    fun updateUserWod(userWod: UserWod){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserWod(userWod)
        }
    }

    fun deleteUserWod(userWod: UserWod){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserWod(userWod)
        }
    }

    fun deleteAllUserWod(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUserWod()
        }
    }
}
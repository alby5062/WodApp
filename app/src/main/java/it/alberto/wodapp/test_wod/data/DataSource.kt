package it.alberto.wodapp.test_wod.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on wodsLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialWodList = wodList(resources)
    private val wodsLiveData = MutableLiveData(initialWodList)

    /* Adds wod to liveData and posts value. */
    fun addWod(wod: Wod) {
        val currentList = wodsLiveData.value
        if (currentList == null) {
            wodsLiveData.postValue(listOf(wod))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, wod)
            wodsLiveData.postValue(updatedList)
        }
    }

    /* Removes wod from liveData and posts value. */
    fun removeWod(wod: Wod) {
        val currentList = wodsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(wod)
            wodsLiveData.postValue(updatedList)
        }
    }

    /* Returns wod given an ID. */
    fun getWodForId(id: Long): Wod? {
        wodsLiveData.value?.let { wods ->
            return wods.firstOrNull { it.id == id }
        }
        return null
    }

    fun getWodList(): LiveData<List<Wod>> {
        return wodsLiveData
    }

    /* Returns a random wod asset for wods that are added. */


    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
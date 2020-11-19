package it.alberto.wodapp.test_wod.wodList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.alberto.wodapp.test_wod.data.DataSource
import it.alberto.wodapp.test_wod.data.Wod

import kotlin.random.Random

class WodsListViewModel(val dataSource: DataSource) : ViewModel() {

    val wodsLiveData = dataSource.getWodList()

    /* If the name and description are present, create new Wod and add it to the datasource */
    fun insertWod(wodName: String?, wodDescription: String?, wodType: String?) {
        if (wodName == null || wodDescription == null || wodType == null) {
            return
        }

        val newWod = Wod(
            Random.nextLong(),
            wodName,
            wodType,
            wodDescription
        )

        dataSource.addWod(newWod)
    }
}

class WodsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WodsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WodsListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
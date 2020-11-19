package it.alberto.wodapp.test_wod.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import it.alberto.wodapp.test_wod.data.DataSource
import it.alberto.wodapp.test_wod.data.Wod

class WodDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a wod that corresponds to an id. */
    fun getWodForId(id: Long) : Wod? {
        return datasource.getWodForId(id)
    }

    /* Queries datasource to remove a wod. */
    fun removeWod(wod: Wod) {
        datasource.removeWod(wod)
    }
}

class WodDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WodDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WodDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
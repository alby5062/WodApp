package it.alberto.wodapp.test_wod.wodList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import it.alberto.wodapp.R
import it.alberto.wodapp.test_wod.addWod.AddWodActivity
import it.alberto.wodapp.test_wod.addWod.WOD_DESCRIPTION
import it.alberto.wodapp.test_wod.addWod.WOD_NAME
import it.alberto.wodapp.test_wod.addWod.WOD_TYPE
import it.alberto.wodapp.test_wod.data.Wod
import it.alberto.wodapp.test_wod.detail.WodDetailActivity


const val WOD_ID = "wod id"

class WodsListActivity : AppCompatActivity() {
    private val newWodsActivityRequestCode = 1
    private val wodsListViewModel by viewModels<WodsListViewModel> {
        WodsListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_wod_main)

        title = "Standard WOD"

        /* Instantiates headerAdapter and wodsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val wodsAdapter = WodsAdapter { wod -> adapterOnClick(wod) }
        val concatAdapter = ConcatAdapter(headerAdapter, wodsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        wodsListViewModel.wodsLiveData.observe(this, {
            it?.let {
                wodsAdapter.submitList(it as MutableList<Wod>)
                headerAdapter.updateWodCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    /* Opens WodDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(wod: Wod) {
        val intent = Intent(this, WodDetailActivity()::class.java)
        intent.putExtra(WOD_ID, wod.id)
        startActivity(intent)
    }

    /* Adds wod to wodList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddWodActivity::class.java)
        startActivityForResult(intent, newWodsActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts wod into viewModel. */
        if (requestCode == newWodsActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val wodName = data.getStringExtra(WOD_NAME)
                val wodType = data.getStringExtra(WOD_TYPE)
                val wodDescription = data.getStringExtra(WOD_DESCRIPTION)

                wodsListViewModel.insertWod(wodName, wodType, wodDescription)
            }
        }
    }
}
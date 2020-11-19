package it.alberto.wodapp.test_wod.detail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.R
import it.alberto.wodapp.test_wod.wodList.WOD_ID


class WodDetailActivity : AppCompatActivity() {

    private val wodDetailViewModel by viewModels<WodDetailViewModel> {
        WodDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wod_detail_activity)

        var currentWodId: Long? = null

        /* Connect variables to UI elements. */
        val wodName: TextView = findViewById(R.id.wod_detail_name)
        val wodType: TextView = findViewById(R.id.wod_detail_type)
        val wodDescription: TextView = findViewById(R.id.wod_detail_description)
        val removeWodButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentWodId = bundle.getLong(WOD_ID)
        }

        /* If currentWodId is not null, get corresponding wod and set name, image and
        description */
        currentWodId?.let {
            val currentWod = wodDetailViewModel.getWodForId(it)
            wodName.text = currentWod?.name
            wodType.text = currentWod?.type
            wodDescription.text = currentWod?.description

            removeWodButton.setOnClickListener {
                if (currentWod != null) {
                    wodDetailViewModel.removeWod(currentWod)
                }
                finish()
            }
        }

    }
}
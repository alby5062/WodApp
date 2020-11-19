package it.alberto.wodapp.test_wod.addWod

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import it.alberto.wodapp.R

const val WOD_NAME = "name"
const val WOD_TYPE = "type"
const val WOD_DESCRIPTION = "description"

class AddWodActivity : AppCompatActivity() {
    private lateinit var addWodName: TextInputEditText
    private lateinit var addWodType: TextInputEditText
    private lateinit var addWodDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_wod_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addWod()
        }
        addWodName = findViewById(R.id.add_wod_name)
        addWodType = findViewById(R.id.add_wod_type)
        addWodDescription = findViewById(R.id.add_wod_description)
    }

    /* The onClick action for the done button. Closes the activity and returns the new wod name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */

    private fun addWod() {
        val resultIntent = Intent()

        if (addWodName.text.isNullOrEmpty() || addWodDescription.text.isNullOrEmpty() || addWodType.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addWodName.text.toString()
            val type = addWodType.text.toString()
            val description = addWodDescription.text.toString()
            resultIntent.putExtra(WOD_NAME, name)
            resultIntent.putExtra(WOD_TYPE, type)
            resultIntent.putExtra(WOD_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}
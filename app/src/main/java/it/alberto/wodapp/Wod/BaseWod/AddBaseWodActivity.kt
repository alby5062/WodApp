package it.alberto.wodapp.Wod.BaseWod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.add_base_wod.*


class AddBaseWodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_base_wod)

        btn_update_wod.setOnClickListener{

                val myDB = DatabaseHelper(this)
                myDB.add(
                    ed_update_name.text.toString().trim { it <= ' ' },
                    ed_update_type.text.toString().trim { it <= ' ' },
                    ed_update_date.text.toString().trim { it <= ' ' })
        }
    }
}
package it.alberto.wodapp.Wod.UserWod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.add_user_wod.*

class AddUserWodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_wod)

        btn_add_wod.setOnClickListener{

            val myDB = DatabaseHelper(this)
            myDB.add(
                ed_add_name.text.toString().trim { it <= ' ' },
                ed_add_type.text.toString().trim { it <= ' ' },
                ed_add_date.text.toString().trim { it <= ' ' })
        }
    }
}
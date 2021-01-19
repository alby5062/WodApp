package it.alberto.wodapp.Wod.UserWod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.Exercise.ExerciseAdapter
import it.alberto.wodapp.Wod.UserWod.Exercise.ExerciseItem
import kotlinx.android.synthetic.main.add_user_wod.*
import kotlinx.android.synthetic.main.exercise_recycle.*


class AddUserWodActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {

    private val exerciseList = ArrayList<ExerciseItem>()
    private val adapter = ExerciseAdapter(exerciseList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_wod)

        val picker = findViewById<View>(R.id.datePicker) as DatePicker
        val date = picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year
        val intent = Intent(this, UserWodActivity::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler_view_exercise.adapter = adapter
        recycler_view_exercise.layoutManager = LinearLayoutManager(this)
        recycler_view_exercise.setHasFixedSize(true)

        btn_add_wod.setOnClickListener{
            intent.putExtra("my_date", date)


            var listEx = ""

            for (i in exerciseList.indices){
                listEx += exerciseList[i].ex + "\\n"
            }
            println(listEx)

            val myDB = DatabaseHelper(this)
            myDB.add(
                ed_add_name.text.toString().trim { it <= ' ' },
                ed_add_type.text.toString().trim { it <= ' ' },
                date.trim { it <= ' ' },
                listEx.trim {it <= ' '}
            )
            startActivity(intent)
        }
    }

    fun insertItemExercise(view: View){
        val index = adapter.itemCount
        val newItem = ExerciseItem(insert_ex.text.toString())
        exerciseList.add(index, newItem)
        adapter.notifyItemRemoved(index)
    }

    private fun lastUsed(){
        val my_date: String? = intent.getStringExtra("my_date")
        val intent = Intent(this, UserWodActivity::class.java)
        intent.putExtra("my_date", my_date)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        lastUsed()
    }

    override fun onSupportNavigateUp(): Boolean {
        lastUsed()
        return true
    }

    override fun onItemClick(position: Int) {
        adapter.remove(position)
        Toast.makeText(this, "Delete $position", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClick(position: Int) {}
}

/*override fun onStart() {
  super.onStart()
  overridePendingTransition(
      R.anim.slide_in_right,
      R.anim.slide_out_left
  )
}

override fun onBackPressed() {
  super.onBackPressed()
  overridePendingTransition(
          R.anim.slide_in_left,
          R.anim.slide_out_right
  )
}*/

package it.alberto.wodapp.Wod.UserWod

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import it.alberto.wodapp.Database.DatabaseHelper
import it.alberto.wodapp.InputCheck
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.Exercise.ExerciseAdapter
import it.alberto.wodapp.Wod.UserWod.Exercise.ExerciseItem
import kotlinx.android.synthetic.main.add_user_wod.*
import kotlinx.android.synthetic.main.exercise_recycle.*


class AddUserWodActivity : AppCompatActivity(), ExerciseAdapter.OnItemClickListener {

    private val exerciseList = ArrayList<ExerciseItem>()
    private val adapter = ExerciseAdapter(exerciseList, this)
    private lateinit var name: String
    private lateinit var type: String
    private lateinit var date: String
    private lateinit var result: String

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_wod)

        val picker = findViewById<View>(R.id.datePicker) as DatePicker
        date = picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler_view_exercise.adapter = adapter
        recycler_view_exercise.layoutManager = LinearLayoutManager(this)
        recycler_view_exercise.setHasFixedSize(true)


        btn_add_wod.setOnClickListener{
            var listEx = ""

            for (i in exerciseList.indices){
                listEx += exerciseList[i].ex + "\\n"
            }

            name = ed_add_name.text.toString()
            type = ed_add_type.text.toString()
            result = ed_add_result.text.toString()
            date = picker.dayOfMonth.toString() + "/" + (picker.month + 1).toString() + "/" + picker.year

            if (InputCheck().inputWorkout(name, type, result)){
                val myDB = DatabaseHelper(this)
                myDB.add(
                    name.trim { it <= ' ' },
                    type.trim { it <= ' ' },
                    date.trim { it <= ' ' },
                    listEx.trim { it <= ' ' },
                    result.trim { it <= ' ' }
                )
                onBackPressed()
            } else {
                Toast.makeText(this, "Insert all data.", Toast.LENGTH_SHORT).show()
            }
        }

        constraint_add_user.setOnTouchListener{ _, _ ->
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            true
        }
    }

    fun insertItemExercise(view: View){
        val index = adapter.itemCount
        val edit_ex = insert_ex.text.toString()
        if (InputCheck().inputExercise(edit_ex)){
            val newItem = ExerciseItem(edit_ex)
            exerciseList.add(index, newItem)
            adapter.notifyItemRemoved(index)
            insert_ex.text.clear()

            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            true

        } else {
            Toast.makeText(this, "No exercise.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, UserWodActivity::class.java)
        setResult(1, intent)
        finish()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClick(position: Int) {
        adapter.remove(position)
        Toast.makeText(this, "Delete $position", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClick(position: Int) {}
}

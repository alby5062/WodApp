package it.alberto.wodapp.Wod.UserWod.Exercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import it.alberto.wodapp.R
import kotlinx.android.synthetic.main.exercise_item.view.*
import kotlin.properties.Delegates

class ExerciseAdapter(
    private val exerciseList: ArrayList<ExerciseItem>,
    private val listener: OnItemClickListener

    ) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private lateinit var deleteButton: Button

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item,
            parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = exerciseList[position]
        holder.textView1.text = currentItem.ex
    }


    override fun getItemCount() = exerciseList.size

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val textView1: TextView = itemView.text_view_1
        init {

            deleteButton = itemView.findViewById(R.id.delete_exercise)

            deleteButton.setOnClickListener {
                val position = bindingAdapterPosition
                remove(position)
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position)
                }
            }
        }
        override fun onClick(v: View?) {}
    }

    fun remove(position : Int){
        exerciseList.removeAt(position)
        notifyItemRemoved(position)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)

    }
}
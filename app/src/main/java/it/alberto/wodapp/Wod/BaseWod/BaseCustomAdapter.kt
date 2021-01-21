package it.alberto.wodapp.Wod.BaseWod

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.alberto.wodapp.R

class BaseCustomAdapter internal constructor(
                    private val activity: Activity,
                    private val context: Context,
                    private val id: ArrayList<String>,
                    private val name: ArrayList<String>,
                    private val type: ArrayList<String>,
                    private val date: ArrayList<String>,
                    private val exercises: ArrayList<String>,
                    private val result: ArrayList<String>
                    ) : RecyclerView.Adapter<BaseCustomAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custom_row_base, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name_txt.text = name[position]
        holder.type_txt.text = type[position]


        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, AddBaseWodActivity::class.java)
            intent.putExtra("id", java.lang.String.valueOf(id[position]))
            intent.putExtra("name", java.lang.String.valueOf(name[position]))
            intent.putExtra("type", java.lang.String.valueOf(type[position]))
            intent.putExtra("date", java.lang.String.valueOf(date[position]))
            intent.putExtra("exercises", java.lang.String.valueOf(exercises[position]))
            intent.putExtra("result", java.lang.String.valueOf(result[position]))
            activity.startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int {
        return id.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_txt: TextView = itemView.findViewById(R.id.name_txt_base)
        var type_txt: TextView = itemView.findViewById(R.id.type_txt_base)

        var mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)

        init {
            val translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim)
            mainLayout.animation = translate_anim
        }



    }

}
package it.alberto.wodapp.Wod.BaseWod.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.BaseWod.data.BaseWod
import kotlinx.android.synthetic.main.custom_row.view.*

class BaseListAdapter: RecyclerView.Adapter<BaseListAdapter.MyViewHolder>() {

    private var baseWodList = emptyList<BaseWod>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = baseWodList[position]
        holder.itemView.text1.text = currentItem.name
        holder.itemView.text2.text = currentItem.type

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListBaseWodFragmentDirections.actionListBaseWodFragmentToAddBaseWodFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return baseWodList.size
    }

    fun setData(baseWod: List<BaseWod>) {
        this.baseWodList = baseWod
        notifyDataSetChanged()
    }

}
package it.alberto.wodapp.Wod.UserWod.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import kotlinx.android.synthetic.main.custom_row.view.*

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    private var userWodList = emptyList<UserWod>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userWodList.size
    }

    fun setData(userWod: List<UserWod>) {
        this.userWodList = userWod
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userWodList[position]
        holder.itemView.text1.text = currentItem.name
        holder.itemView.text2.text = currentItem.type

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListUserWodFragmentDirections.actionListUserWodFragmentToUpdateUserWodFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
}
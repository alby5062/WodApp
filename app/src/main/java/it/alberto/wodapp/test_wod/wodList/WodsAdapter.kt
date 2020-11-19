package it.alberto.wodapp.test_wod.wodList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import it.alberto.wodapp.R
import it.alberto.wodapp.test_wod.data.Wod

class WodsAdapter(private val onClick: (Wod) -> Unit) :
    ListAdapter<Wod, WodsAdapter.WodViewHolder>(WodDiffCallback) {

    /* ViewHolder for Wod, takes in the inflated view and the onClick behavior. */
    class WodViewHolder(itemView: View, val onClick: (Wod) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val wodTextView: TextView = itemView.findViewById(R.id.wod_text)
        private val wodTextViewType: TextView = itemView.findViewById(R.id.wod_type)
        //private val wodImageView: ImageView = itemView.findViewById(R.id.wod_image)
        private var currentWod: Wod? = null

        init {
            itemView.setOnClickListener {
                currentWod?.let {
                    onClick(it)
                }
            }
        }

        /* Bind wod name and image. */
        fun bind(wod: Wod) {
            currentWod = wod

            wodTextView.text = wod.name
            wodTextViewType.text = wod.type
        }
    }

    /* Creates and inflates view and return WodViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wod_item, parent, false)
        return WodViewHolder(view, onClick)
    }

    /* Gets current wod and uses it to bind view. */
    override fun onBindViewHolder(holder: WodViewHolder, position: Int) {
        val wod = getItem(position)
        holder.bind(wod)

    }
}

object WodDiffCallback : DiffUtil.ItemCallback<Wod>() {
    override fun areItemsTheSame(oldItem: Wod, newItem: Wod): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Wod, newItem: Wod): Boolean {
        return oldItem.id == newItem.id
    }
}
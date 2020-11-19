package it.alberto.wodapp.ui.wod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.alberto.wodapp.R

class WodFragment : Fragment() {

    private lateinit var wodViewModel: WodViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        wodViewModel =
                ViewModelProvider(this).get(WodViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_wod, container, false)
        val textView: TextView = root.findViewById(R.id.text_wod)
        wodViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
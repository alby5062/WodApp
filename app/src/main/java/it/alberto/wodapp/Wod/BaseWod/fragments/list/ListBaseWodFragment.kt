package it.alberto.wodapp.Wod.BaseWod.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.BaseWod.viewmodel.BaseWodViewModel
import kotlinx.android.synthetic.main.fragment_list_base_wod.view.*
import kotlinx.coroutines.InternalCoroutinesApi

class ListBaseWodFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var mBaseWodViewModel: BaseWodViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_list_base_wod, container, false)

        // Recycler View
        val adapter = BaseListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // BaseWodViewModel
        mBaseWodViewModel = ViewModelProvider(this).get(BaseWodViewModel::class.java)
        mBaseWodViewModel.readAllData.observe(viewLifecycleOwner, { baseWod ->
            adapter.setData(baseWod)
        })

        view.add_user_wod_fbutton.setOnClickListener{
            findNavController().navigate(R.id.action_listBaseWodFragment_to_addUserWodFragment)
        }

        return view
    }
}
package it.alberto.wodapp.Wod.UserWod.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.viewmodel.UserWodViewModel
import kotlinx.android.synthetic.main.fragment_list_user_wod.view.*
import kotlinx.coroutines.InternalCoroutinesApi


class ListUserWodFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var mUserWodViewModel: UserWodViewModel


    @InternalCoroutinesApi
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_user_wod, container, false)

        val adapter = UserListAdapter()
        val recyclerView  = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserWodViewModel = ViewModelProvider(this).get(UserWodViewModel::class.java)
        mUserWodViewModel.readAllData.observe(viewLifecycleOwner, { userWod ->
            adapter.setData(userWod)
        })

        /*mUserWodViewModel.findWodWithDateClicked.observe(viewLifecycleOwner,{ userWod ->
            adapter.setData(userWod)
        })*/

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listUserWodFragment_to_updateUserWodFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @InternalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUserWod()
        }
        return super.onOptionsItemSelected(item)
    }

    @InternalCoroutinesApi
    private fun deleteAllUserWod() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserWodViewModel.deleteAllUserWod()
            Toast.makeText(
                    requireContext(),
                    "Successfully removed everything",
                    Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}
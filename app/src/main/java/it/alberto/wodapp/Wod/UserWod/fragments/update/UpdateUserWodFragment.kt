package it.alberto.wodapp.Wod.UserWod.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import it.alberto.wodapp.Wod.UserWod.viewmodel.UserWodViewModel
import kotlinx.android.synthetic.main.fragment_add_user_wod.view.*
import kotlinx.android.synthetic.main.fragment_update_user_wod.*
import kotlinx.coroutines.InternalCoroutinesApi


class UpdateUserWodFragment : Fragment() {

    private val args by navArgs<UpdateUserWodFragmentArgs>()

    @InternalCoroutinesApi
    private lateinit var mUserWodViewModel: UserWodViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_user_wod, container, false)

        mUserWodViewModel = ViewModelProvider(this).get(UserWodViewModel::class.java)

        view.update_text1.setText(args.currentUser.name)
        view.update_text2.setText(args.currentUser.type)
        view.update_text4.setText(args.currentUser.date)
        view.update_text3.setText(args.currentUser.description)

        view.update_user_wod_button.setOnClickListener{
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    @InternalCoroutinesApi
    private fun updateItem(){
        val text1 = update_text1.text.toString()
        val text2 = update_text2.text.toString()
        val text3 = update_text3.text.toString()
        val text4 = update_text4.text.toString()


        if (inputCheck(text1,text2,text3,text4)){

            val updatedUserWod = UserWod(args.currentUser.id, text1,text2,text3,text4)
            mUserWodViewModel.updateUserWod(updatedUserWod)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateUserWodFragment_to_listUserWodFragment)
        }else{
            Toast.makeText(requireContext(), "Wrong", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(text1: String, text2: String, text3: String, text4: String): Boolean{
        return !(TextUtils.isEmpty((text1)) && TextUtils.isEmpty(text2) && TextUtils.isEmpty(text3) && TextUtils.isEmpty(text4))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @InternalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUserWod()
        }
        return super.onOptionsItemSelected(item)
    }

    @InternalCoroutinesApi
    private fun deleteUserWod() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserWodViewModel.deleteUserWod(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateUserWodFragment_to_listUserWodFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.name}?")
        builder.create().show()
    }
}
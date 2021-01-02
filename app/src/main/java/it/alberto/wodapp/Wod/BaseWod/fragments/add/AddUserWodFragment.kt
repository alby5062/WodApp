package it.alberto.wodapp.Wod.BaseWod.fragments.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import it.alberto.wodapp.Login.DashboardActivity
import it.alberto.wodapp.R
import it.alberto.wodapp.Wod.UserWod.UserWodActivity
import it.alberto.wodapp.Wod.UserWod.data.UserWod
import it.alberto.wodapp.Wod.UserWod.viewmodel.UserWodViewModel
import kotlinx.android.synthetic.main.fragment_add_user_wod.*
import kotlinx.android.synthetic.main.fragment_add_user_wod.view.*
import kotlinx.coroutines.InternalCoroutinesApi


class AddUserWodFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var mUserWodViewModel: UserWodViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_user_wod, container, false)

        mUserWodViewModel = ViewModelProvider(this).get(UserWodViewModel::class.java)

        view.update_user_wod_button.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    @InternalCoroutinesApi
    private fun insertDataToDatabase() {

        val text1 = update_text1.text.toString()
        val text2 = update_text2.text.toString()
        val text3 = update_text3.text.toString()
        val text4 = update_text4.text.toString()

        if (inputCheck(text1, text2, text3)){
            val userWod = UserWod(0, text1, text2, text3, text4)

            mUserWodViewModel.addUserWod(userWod)
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
            //moveToNewActivity()

        }else{
            Toast.makeText(requireContext(), "Wrong", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(text1: String, text2: String, text3: String): Boolean{
        return !(TextUtils.isEmpty((text1)) && TextUtils.isEmpty(text2) && TextUtils.isEmpty(text3))
    }

    private fun moveToNewActivity() {
        val i = Intent(activity, UserWodActivity::class.java)
        startActivity(i)
        (activity as Activity?)!!.overridePendingTransition(0, 0)
    }
}
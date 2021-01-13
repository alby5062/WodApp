package it.alberto.wodapp.Utility.Timer

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

import it.alberto.wodapp.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}
package com.example.chat_bidireccional.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.chat_bidireccional.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String TAG = "SettingsFragment";

    @Override
    public void onCreatePreferences(Bundle saveInstance, String rootKey){
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}

package com.devatrii.statussaver.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devatrii.statussaver.R
import com.devatrii.statussaver.databinding.FragmentMediaBinding
import com.devatrii.statussaver.databinding.FragmentSettingsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSettings.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSettings : Fragment() {
    private val binding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root
}
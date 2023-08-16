package com.ks.ks_quizlet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ks.ks_quizlet.databinding.FragmentResultsBinding

class Results : Fragment() {
    private lateinit var binding: FragmentResultsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            val route = ResultsDirections.actionResultsToWelcome()
            findNavController().navigate(route)
        }
    }
}
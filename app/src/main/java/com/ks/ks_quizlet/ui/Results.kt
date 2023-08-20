package com.ks.ks_quizlet.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ks.ks_quizlet.R
import com.ks.ks_quizlet.databinding.FragmentResultsBinding
import com.ks.ks_quizlet.ui.arch.QuestionsViewModel
import kotlinx.coroutines.launch

class Results : Fragment() {
    private lateinit var binding: FragmentResultsBinding
    private val viewModel by activityViewModels<QuestionsViewModel>()

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
        viewModel.calculateScores()

        binding.apply {
            completedView.apply {
                viewResultsBtn.setOnClickListener {
                    scoresView.root.isVisible = true
                    completedView.root.isVisible = false
                }
            }

            scoresView.apply {
                retryBtn.setOnClickListener {
                    val route = ResultsDirections.actionResultsToWelcome()
                    findNavController().navigate(route)
                }
                lifecycleScope.launch {
                    viewModel.totalScorePercentage.collect {
                        val color = if (it < 50.0) {
                            ContextCompat.getColor(requireContext(), R.color.low_score)
                        } else {
                            ContextCompat.getColor(requireContext(), R.color.normal_blue)
                        }
                        scoresView.scoresTxt.setTextColor(color)
                        scoresView.scoresTxt.text = requireContext().getString(
                            R.string.scores_text,
                            it,
                            "%"
                        )
                    }

                }
            }
        }
    }
}
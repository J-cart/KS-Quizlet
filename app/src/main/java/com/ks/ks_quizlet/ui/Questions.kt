package com.ks.ks_quizlet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.ks.ks_quizlet.R
import com.ks.ks_quizlet.databinding.FragmentQuestionsBinding
import com.ks.ks_quizlet.ui.adapter.ViewPagerAdapter
import com.ks.ks_quizlet.util.AnswerRelay
import com.ks.ks_quizlet.ui.arch.QuestionsViewModel
import kotlinx.coroutines.launch


class Questions : Fragment(), AnswerRelay {
    private lateinit var binding: FragmentQuestionsBinding
    private var sliderDotsPanel: LinearLayout? = null
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val viewModel by activityViewModels<QuestionsViewModel>()

    private var dotscount = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadQuestions()
        sliderDotsPanel = binding.sliderDots
        lifecycleScope.launch {
            viewModel.questionsList.collect{
                viewPagerAdapter = ViewPagerAdapter(requireContext(),this@Questions,it)
            }
        }

        dotscount = viewPagerAdapter.count
        binding.viewpager.adapter = viewPagerAdapter

        val dots = arrayOfNulls<ImageView>(dotscount)
        initDefaultSlider(dots)
        sliderChangeListener(dots)

    }

    private fun initDefaultSlider(dots: Array<ImageView?>) {
        for (i in 0 until dotscount) {
            dots[i] = ImageView(requireContext())
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.inactive_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotsPanel!!.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.active_dot
            )
        )
    }

    private fun sliderChangeListener(dots: Array<ImageView?>) {
        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                binding.apply {

                    backBtn.isVisible = position != 0
                    backBtn.setOnClickListener {
                        viewpager.currentItem = binding.viewpager.currentItem - 1
                    }

                    nextBtn.apply {
                        if (position == viewPagerAdapter.count - 1) {
                            text = "Finish"
                            setOnClickListener {
                                val route = QuestionsDirections.actionQuestionsToResults()
                                findNavController().navigate(route)
                            }
                        } else {
                            text = "Next"
                            setOnClickListener {
                                binding.viewpager.currentItem = binding.viewpager.currentItem + 1
                            }
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.inactive_dot
                        )
                    )
                }
                for (i in 0 until position + 1) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.active_dot
                        )
                    )
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                // TODO("Not yet implemented")
            }
        })
    }

    override fun sendAnswers(answers: Map<Int, String>) {
        viewModel.updateAnswers(answers)
    }
}
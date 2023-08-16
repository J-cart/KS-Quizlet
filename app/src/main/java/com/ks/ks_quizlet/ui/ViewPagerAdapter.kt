package com.ks.ks_quizlet.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.viewpager.widget.PagerAdapter
import com.ks.ks_quizlet.R

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null


    private val descArray = arrayOf(
        "Track Your Phone3",
        "Lock Your Phone4",
        "Lock Your Phone46",
        "Ring Your Device"
    )
    private val titleArray = arrayOf(
        "Stay Connected, Never Lost: Unlock the Power to Effortlessly Track Your Phone and Always Stay in Control of Your Device's Location!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data2!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data!3",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data4!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data5!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data6!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data!7",
        /*"Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data4!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data!4",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data5!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data56!",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data!67",
        "Secure Your World, Anytime, Anywhere: Empower Yourself to Easily Lock Your Phone and Safeguard Your Personal Data!67",*/
        "Find Your Lost Phone in a Jiffy: Ring Your Phone with Confidence  and Never Let It Wander Far From Your Reach!"
    )

    override fun getCount(): Int {
        return titleArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.questions_layout, container, false)
        val questionTxt = view.findViewById<TextView>(R.id.question_text)
        val answerGrp = view.findViewById<RadioGroup>(R.id.radioGroup)

        answerGrp.children.forEach {
            val answerBtn = it as RadioButton
            descArray.forEach {answer->
                answerBtn.text = answer
            }

        }
        questionTxt.text = titleArray[position]
        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)

    }

}

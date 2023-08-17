package com.ks.ks_quizlet.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.ks.ks_quizlet.R

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null


    private val options = listOf(
        "Track Your Phone3",
        "Lock Your Phone4",
        "Lock Your Phone46",
        "Ring Your Device"
    )
    private val questions = mutableMapOf<String, Map<String, List<String>>>(
        "what is your name?" to mapOf("unknown" to options.shuffled()),
        "what class are you ?" to mapOf("ion know" to options.shuffled()),
        "Any Hobby at all?" to mapOf("dunno" to options.shuffled()),
        "Are you young?" to mapOf("yh" to options.shuffled()),
        "Are you smart?" to mapOf("pretty much" to options.shuffled()),
        "Are you ambitious?" to mapOf("not sure" to options.shuffled()),
        "Are you a geek?" to mapOf("no" to options.shuffled()),
        "Do you like coding?" to mapOf("yes" to options.shuffled())
    )

    private val keyQuestions = questions.keys.toList()
    private val answerValues = questions.values.toList()

    private val answerList = mutableMapOf<Int,String>()


    override fun getCount(): Int {
        return keyQuestions.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.questions_layout, container, false)
        val questionTxt = view.findViewById<TextView>(R.id.question_text)
        val answerGrp = view.findViewById<RadioGroup>(R.id.radioGroup)
        val answerBtn1 = view.findViewById<RadioButton>(R.id.radio_button_1)
        val answerBtn2 = view.findViewById<RadioButton>(R.id.radio_button_2)
        val answerBtn3 = view.findViewById<RadioButton>(R.id.radio_button_3)
        val answerBtn4 = view.findViewById<RadioButton>(R.id.radio_button_4)


        questionTxt.text = keyQuestions[position]
        answerBtn1.text = answerValues[position].values.toList()[0][0]
        answerBtn2.text = answerValues[position].values.toList()[0][1]
        answerBtn3.text = answerValues[position].values.toList()[0][2]
        answerBtn4.text = answerValues[position].values.toList()[0][3]

        answerGrp.setOnCheckedChangeListener { radioGroup, i ->
            val one = radioGroup.findViewById<View>(i) as RadioButton
            Log.d("QUESTION", "instantiateItem: ${one.text}, ${one.isChecked}")
            answerList[keyQuestions.indexOf(questionTxt.text)] = one.text.toString()
            Log.d("QUESTION", "instantiateItem: ${answerList}")

        }
        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)

    }

}

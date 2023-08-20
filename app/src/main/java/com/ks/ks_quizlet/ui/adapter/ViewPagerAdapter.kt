package com.ks.ks_quizlet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.ks.ks_quizlet.R
import com.ks.ks_quizlet.util.AnswerRelay
import com.ks.ks_quizlet.data.model.Questions

class ViewPagerAdapter(
    private val context: Context,
    private val answerRelay: AnswerRelay,
    private val questions: List<Questions>
) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val answerList = mutableMapOf<Int,String>()

    override fun getCount(): Int {
        return questions.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.questions_layout, container, false)
        val questionNumber = view.findViewById<TextView>(R.id.question_number)
        val questionTxt = view.findViewById<TextView>(R.id.question_text)
        val answerGrp = view.findViewById<RadioGroup>(R.id.radioGroup)
        val answerBtn1 = view.findViewById<RadioButton>(R.id.radio_button_1)
        val answerBtn2 = view.findViewById<RadioButton>(R.id.radio_button_2)
        val answerBtn3 = view.findViewById<RadioButton>(R.id.radio_button_3)
        val answerBtn4 = view.findViewById<RadioButton>(R.id.radio_button_4)


        questionNumber.text = context.getString(R.string.question_number,position+1)
        questionTxt.text = questions[position].question
        answerBtn1.text = questions[position].options[0]
        answerBtn2.text = questions[position].options[1]
        answerBtn3.text = questions[position].options[2]
        answerBtn4.text = questions[position].options[3]

        answerGrp.setOnCheckedChangeListener { radioGroup, i ->
            val one = radioGroup.findViewById<View>(i) as RadioButton
            val currentQuestion = questions.find {
                it.question == questionTxt.text
            } ?: throw IllegalAccessException("The odds of this were quite low but, No question found here ...")

            answerList[questions.indexOf(currentQuestion)] = one.text.toString()
            answerRelay.sendAnswers(answerList.toMap())
        }
        container.addView(view)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as ConstraintLayout)

    }

}

package com.ks.ks_quizlet.ui.arch

import androidx.lifecycle.ViewModel
import com.ks.ks_quizlet.data.model.Questions
import com.ks.ks_quizlet.data.QuestionsBank
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class QuestionsViewModel : ViewModel() {
    var questionsList = MutableStateFlow<List<Questions>>(emptyList())
        private set
    var answersMap = MutableStateFlow<Map<Int, String>>(mapOf())
        private set
    var totalScorePercentage = MutableStateFlow(0F)
        private set


    fun loadQuestions() {
        questionsList.update {
            QuestionsBank.getAllQuestions()
        }
    }

    fun updateAnswers(answers: Map<Int, String>) {
        answersMap.value = answers
    }

    fun calculateScores() {
        var scoreCount = 0

        answersMap.value.onEachIndexed { index, entry ->
            val answer = questionsList.value[index].answer
            if (answer == entry.value) {
                scoreCount+=1
            }
        }

        totalScorePercentage.value  = ((scoreCount/questionsList.value.size.toFloat()) * 100)
    }

}
package com.ks.ks_quizlet.util

interface AnswerRelay{
    fun sendAnswers(answers:Map<Int,String>)
}
package com.ks.ks_quizlet.data

import com.ks.ks_quizlet.data.model.Questions

object QuestionsBank {

    private val options = listOf(
        "unknown",
        "ion know",
        "dunno",
        "yh"
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

    fun getAllQuestions():List<Questions>{
        return keyQuestions.mapIndexed { index, string ->
            Questions(
                question = string,
                answer = answerValues[index].keys.toList()[0],
                options = answerValues[index].values.toList()[0]
            )
        }

    }

}
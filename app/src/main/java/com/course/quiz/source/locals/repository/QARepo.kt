package com.course.quiz.source.locals.repository

import androidx.lifecycle.MutableLiveData
import com.course.quiz.data.models.AnalyzeQuestion
import com.course.quiz.data.models.QuestionAnswers
import com.course.quiz.source.locals.dao.QuestionsAnswersDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
 import kotlin.collections.ArrayList

class QARepo(private val questionsAnswersDAO: QuestionsAnswersDAO) {
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    var liveDataQuestionAnswers = MutableLiveData<List<QuestionAnswers>>()
    var liveDataCorrectAnswer = MutableLiveData<List<AnalyzeQuestion>>()
    var liveDataUserAnswer = MutableLiveData<List<AnalyzeQuestion>>()

    suspend fun getAnswerByQuestId(questno: Int) = withContext(defaultDispatcher) {
        liveDataQuestionAnswers.postValue(questionsAnswersDAO.getAnswerByQuestion(questno))
    }

    suspend fun resetAnsweres(): Boolean {
        withContext(defaultDispatcher) {
            questionsAnswersDAO.resetAnswers()

        }
        return true
    }

    suspend fun updateCurrentQuestion(currentQuestionAnswers: ArrayList<QuestionAnswers>) {
        withContext(defaultDispatcher) {
            try {
                questionsAnswersDAO.update(currentQuestionAnswers)
            } catch (e: Exception) {
            }
        }
    }


    fun getAllAnswer() : List<AnalyzeQuestion>  {

        return      questionsAnswersDAO.getAllAnswers()

    }

    fun getCorrectAnswerByQuestNo(questionNo: Int): List<AnalyzeQuestion> {

        return questionsAnswersDAO.getCorrectQuestionAnswers(questionNo)

    }

    fun getUserAnswerByQuestNo(questionNo: Int): List<AnalyzeQuestion> {
        return questionsAnswersDAO.getUserCorrectAnswers(questionNo)

    }
}
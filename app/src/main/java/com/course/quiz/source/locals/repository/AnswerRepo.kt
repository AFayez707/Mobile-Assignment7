package com.course.quiz.source.locals.repository

import androidx.lifecycle.MutableLiveData
import com.course.quiz.data.models.Answer
import com.course.quiz.source.locals.dao.AnswersDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnswerRepo(private val answersDAO: AnswersDAO) {
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    var liveDataResult = MutableLiveData<Int>()




    suspend fun saveUserAnswer(answer : Answer) = withContext(defaultDispatcher){
         answersDAO.insert(answer)
    }

    suspend fun calcResult() {
        withContext(defaultDispatcher){
            liveDataResult.postValue(answersDAO.calcResult())
        }
    }

   suspend fun clearAnswers() {
        withContext(defaultDispatcher){
            answersDAO.clearAnswers()
        }
    }

}
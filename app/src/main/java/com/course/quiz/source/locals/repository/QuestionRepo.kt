package com.course.quiz.source.locals.repository

import androidx.lifecycle.MutableLiveData
import com.course.quiz.data.models.Question
import com.course.quiz.source.locals.dao.QuestionsDAO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionRepo(private val questionsDao: QuestionsDAO) {
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    val questionMLD = MutableLiveData<Question>()
    val questionCountMLD = MutableLiveData<Int>()

    suspend fun getQuestionByNo(questNo: Int) = withContext(defaultDispatcher) {
        questionMLD.postValue(questionsDao.getQuestionByNo(questNo))
    }

    suspend fun insert(question: Question) {
        questionsDao.insert(question)
    }

    suspend fun getQuestionsSize() {
        withContext(defaultDispatcher) {
            questionCountMLD.postValue(questionsDao.getQuestionsSize())
        }
    }
}
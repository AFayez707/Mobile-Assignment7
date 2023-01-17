package com.course.quiz.source.locals.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.course.quiz.data.models.QuestionAnswersVu


interface QuestionsAnswersVUDAO {
    @Query("Select * from QuestionAnswersVu where questionNo")
    fun getAllAnswers(): LiveData<List<QuestionAnswersVu>>

    @Query("Select * from QuestionAnswersVu where questionNo = :questNo")
    fun getAnswerByQuestion(questNo: Int): List<QuestionAnswersVu>
}
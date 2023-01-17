package com.course.quiz.source.locals.dao

import androidx.room.*
import com.course.quiz.data.models.Answer

@Dao
interface AnswersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(answer: Answer)
    @Query("select count(questionNo) from UserAnswerTable where correctAnswer=userAnswer" )
    fun calcResult() : Int
    @Query("delete from UserAnswerTable")
    fun clearAnswers()


}
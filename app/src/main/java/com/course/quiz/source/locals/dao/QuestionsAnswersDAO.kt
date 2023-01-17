package com.course.quiz.source.locals.dao

import androidx.room.*
import com.course.quiz.data.models.*

@Dao
interface QuestionsAnswersDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(questionAnswers: QuestionAnswers)

    @Insert
    fun insert(questionAnswers: List<QuestionAnswers>)

    @Query("select distinct questionTable.questionNo,questionTable.questionText,answerText   from questionAnswerTable join questionTable on questionTable.questionNo = questionAnswerTable.questionNo ")
    fun getAllAnswers(): List<AnalyzeQuestion>

    @Query("Select * from questionAnswerTable where questionNo = :questNo")
    fun getAnswerByQuestion(questNo: Int): List<QuestionAnswers>

    @Query("update questionAnswerTable set selected = 0")
    fun resetAnswers()

    @Query("select  questionTable.questionNo,questionTable.questionText,answerText   from questionAnswerTable join questionTable on questionTable.questionNo = questionAnswerTable.questionNo  where isCorrectAnswer=1 and questionTable.questionNo  = :questionNo")
    fun getCorrectQuestionAnswers(questionNo:Int) : List<AnalyzeQuestion>

    @Query("select  questionTable.questionNo,questionTable.questionText,answerText   from questionAnswerTable join questionTable on questionTable.questionNo = questionAnswerTable.questionNo  where selected=1 and questionTable.questionNo  = :questionNo")
    fun getUserCorrectAnswers(questionNo:Int) : List<AnalyzeQuestion>
    @Update
    fun update(it: List<QuestionAnswers>)
}
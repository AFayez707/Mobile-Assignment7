package com.course.quiz.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserAnswerTable")
data class Answer( @PrimaryKey val questionNo : Int , val correctAnswer: String, var userAnswer : String) {
 }

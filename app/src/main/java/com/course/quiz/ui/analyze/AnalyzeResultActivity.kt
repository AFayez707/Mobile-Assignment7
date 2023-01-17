package com.course.quiz.ui.analyze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.course.quiz.R
import com.course.quiz.adapter.AnalysisAdapter
import com.course.quiz.source.locals.QuizDB
import com.course.quiz.source.locals.repository.QARepo

class AnalyzeResultActivity : AppCompatActivity() {
    private lateinit var   repositoryQuestionAnswers: QARepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analyze_result)

        val recyclerview = findViewById<RecyclerView>(R.id.rvResultAnalysis)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val daoQuestAnswer = QuizDB.getDatabase(application).getQuestionAnswerDao()
        repositoryQuestionAnswers   = QARepo(daoQuestAnswer)
        var data   = repositoryQuestionAnswers.getAllAnswer()

        val adapter = AnalysisAdapter(data,repositoryQuestionAnswers)

        recyclerview.adapter = adapter

    }
}
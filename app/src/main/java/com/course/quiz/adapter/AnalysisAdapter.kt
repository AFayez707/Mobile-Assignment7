package com.course.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.course.quiz.data.models.AnalyzeQuestion
import com.course.quiz.databinding.AnalyzeQuesitonItemBinding
import com.course.quiz.source.locals.repository.QARepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnalysisAdapter(

    private val set: List<AnalyzeQuestion>,
    private val qaRepo: QARepo

) :
    RecyclerView.Adapter<AnalysisAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: AnalyzeQuesitonItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AnalyzeQuesitonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {

            with(set[position]) {

                binding.tvQuestion.text = this.questionText
                CoroutineScope(Dispatchers.IO).launch {
                    var correcAnser =
                        qaRepo.getCorrectAnswerByQuestNo(set[position].questionNo)
                    correcAnser.forEach() {
                        binding.tvCorrectAnswer.text = it.answerText
                    }

                    correcAnser.forEach() {
                        binding.tvUserAnswer.text = it.answerText
                    }

                }


            }
        }
    }

    override fun getItemCount(): Int {
        return set.size
    }
}

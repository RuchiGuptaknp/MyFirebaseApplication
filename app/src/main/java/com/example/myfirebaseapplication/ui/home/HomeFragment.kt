package com.example.myfirebaseapplication.ui.home


import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.example.myfirebaseapplication.MainActivity
import com.example.myfirebaseapplication.R

import com.example.myfirebaseapplication.Utility.Constant


import com.example.myfirebaseapplication.databinding.FragmentHomeBinding
import com.example.myfirebaseapplication.model.Questions



class HomeFragment : Fragment(),View.OnClickListener {
    private var mCorrectCount = 0
    private var currentPosition = 1
    private var selectedOptionPosition = 0
    private lateinit var questions: Questions
    private var questionList: ArrayList<Questions>? = null


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView()

        return root
    }

    private fun initView() {
        setQuestionList()
        binding.tvOption.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)
        binding.textHome.setOnClickListener(this)

    }

    private fun setQuestionList() {
        questionList = Constant.getQuestionList()
        Log.d("questionList", questionList!!.size.toString())

        questions = questionList!![currentPosition - 1]
        defaultOptionView()
        if (currentPosition==questionList!!.size){
            binding.textHome.text=getString(R.string.tv_finish)

        }else{
            binding.textHome.text=getString(R.string.tv_submit)
        }
        binding.progressBar.progress = currentPosition
        binding.tvProgress.text = "$currentPosition" + "/" + questionList!!.size.toString()
        binding.textUpdate.text = questions.questions
        binding.tvOption.text = questions.option1
        binding.tvOption2.text = questions.option2
        binding.tvOption3.text = questions.option3
        binding.tvOption4.text = questions.option4
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOption)
        options.add(1, binding.tvOption2)
        options.add(2, binding.tvOption3)
        options.add(3, binding.tvOption4)
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = getDrawable(
                requireActivity(),
               R.drawable.btn_shape
            )
        }


    }

    private fun selectedOptionView(textView: TextView, selectedOptionView: Int) {
        defaultOptionView()
        selectedOptionPosition = selectedOptionView
        textView.setTextColor(Color.parseColor("#7A8089"))
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.background = getDrawable(
            requireActivity(),
            R.drawable.bg_selector_text
        )


    }

    private fun answerView(answer: Int, image: Int) {
        when (answer) {
            1 -> {
                binding.tvOption.background = ContextCompat.getDrawable(requireActivity(), image)
            }
            2 -> {
                binding.tvOption2.background = ContextCompat.getDrawable(requireActivity(), image)
            }
            3 -> {
                binding.tvOption3.background = ContextCompat.getDrawable(requireActivity(), image)
            }
            4 -> {
                binding.tvOption4.background = ContextCompat.getDrawable(requireActivity(), image)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
          R.id.tvOption -> {
                selectedOptionView(binding.tvOption, 1)
            }
         R.id.tvOption2 -> {
                selectedOptionView(binding.tvOption2, 2)
            }
           R.id.tvOption3 -> {
                selectedOptionView(binding.tvOption3, 3)
            }
         R.id.tvOption4 -> {
                selectedOptionView(binding.tvOption4, 4)
            }
           R.id.textHome -> {
                if (selectedOptionPosition == 0) {
                    currentPosition++

                    when {
                        currentPosition <= questionList!!.size -> {
                            setQuestionList()

                        }
                        else -> {
                            val bundle=Bundle()
                            bundle.putString(Constant.USER_NAME,(activity as MainActivity).userName)
                            bundle.putString(Constant.TOTAL_QUESTION,questionList!!.size.toString())
                            bundle.putString(Constant.CORRECT_ANSWER,mCorrectCount.toString())
                            Navigation.findNavController(binding.root).navigate(R.id.nav_home_to_result,bundle)

                           // (activity as MainActivity).showToast("You have successfully completed quiz")
                        }


                    }
                } else {
                    val question = questionList!!.get(currentPosition - 1)
                    if (question.correctAnswer != selectedOptionPosition) {
                        answerView(
                           selectedOptionPosition,
                          R.drawable.wrong_bg
                        )
                    }else{
                        mCorrectCount++
                    }
                    answerView(
                        question.correctAnswer,
                      R.drawable.correct_answer_bg
                    )
                    if (currentPosition==questionList!!.size){
                        binding.textHome.text=getString(R.string.tv_finish)

                    }else{
                        binding.textHome.text=getString(R.string.tv_go_to_next)
                    }
                    selectedOptionPosition=0
                }

            }
        }
    }
}

package com.example.myfirebaseapplication.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myfirebaseapplication.MainActivity
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.Utility.Constant
import com.example.myfirebaseapplication.Utility.showToast
import com.example.myfirebaseapplication.databinding.FragmentGalleryBinding
import com.example.myfirebaseapplication.model.Questions
import com.google.firebase.firestore.FirebaseFirestore

class GalleryFragment : Fragment() {
    private var userName:String?=null
    private var totalQuestion:String?=null
    private var correctAnswer:String?=null

    private val  binding by lazy { FragmentGalleryBinding.inflate(layoutInflater) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = binding.root
        initView()


        return root
    }

    private fun initView() {
        arguments?.let{

            userName=it.getString(Constant.USER_NAME)
            totalQuestion=it.getString(Constant.TOTAL_QUESTION)
            correctAnswer=it.getString(Constant.CORRECT_ANSWER)
        }
        binding.tvUserName.text=userName
        binding.tvScore.text="YourScore is ${correctAnswer}${" "}out of${totalQuestion}"


        binding.BtnSubmit.setOnClickListener {
        Navigation.findNavController(binding.root)
        .navigate(R.id.nav_result_to_nav_home)

}

        }

    }





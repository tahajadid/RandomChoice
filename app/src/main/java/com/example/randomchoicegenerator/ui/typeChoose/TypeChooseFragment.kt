package com.example.randomchoicegenerator.ui.typeChoose

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentSplashScreenBinding
import com.example.randomchoicegenerator.databinding.FragmentTypeChooseBinding
import com.example.spicyanimation.SpicyAnimation

class TypeChooseFragment : Fragment() {

    private lateinit var binding: FragmentTypeChooseBinding
    private var confirmed = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_type_choose,
            container,
            false
        )

        initComponent()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

    private fun initComponent() {
        initBackground()

        // Section 3
        binding.choiceOne.setOnClickListener {
            initBackground()
            confirmed = 1
            binding.choiceOne.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected) }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on) }
        }

        // Section 3
        binding.choiceTwo.setOnClickListener {
            initBackground()
            confirmed = 1
            binding.choiceTwo.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected) }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on) }
        }

        // Section 3
        binding.choiceThree.setOnClickListener {
            initBackground()
            confirmed = 1
            binding.choiceThree.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected) }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on) }
        }

        // Section 3
        binding.nextBtn.setOnClickListener {
            if(confirmed==0){
                binding.error.visibility = View.VISIBLE
                SpicyAnimation().fadeToUp(binding.nextBtn,30F,200)
            }else{

            }
        }

    }

    fun initBackground(){
        confirmed = 0
        binding.choiceOne.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected) }

        binding.choiceTwo.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected) }

        binding.choiceThree.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected) }

        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_off) }
    }
}

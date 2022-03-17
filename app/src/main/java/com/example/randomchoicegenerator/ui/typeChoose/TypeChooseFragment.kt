package com.example.randomchoicegenerator.ui.typeChoose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentTypeChooseBinding
import com.example.spicyanimation.SpicyAnimation

class TypeChooseFragment : Fragment() {

    private lateinit var binding: FragmentTypeChooseBinding
    private var isSelected = 0

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

        SpicyAnimation().fadeToDown(binding.separatorTop, 20F, 400)
        initComponent()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initComponent() {
        initBackground()

        SpicyAnimation().fadeToDown(binding.descriptionNumbers,20F,600)
        SpicyAnimation().fadeToDown(binding.descriptionNames,20F,800)
        SpicyAnimation().fadeToDown(binding.descriptionAutres,20F,1000)

        // Section 1
        binding.choiceOne.setOnClickListener {
            initBackground()
            isSelected = 1
            binding.choiceOne.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected)
            }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
            }
        }

        // Section 2
        binding.choiceTwo.setOnClickListener {
            initBackground()
            isSelected = 2
            binding.choiceTwo.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected)
            }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
            }
        }

        // Section 3
        binding.choiceThree.setOnClickListener {
            initBackground()
            isSelected = 2
            binding.choiceThree.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected)
            }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
            }
        }

        // Section next button
        binding.nextBtn.setOnClickListener {

            when (isSelected) {
                0 -> {
                    binding.error.visibility = View.VISIBLE
                    SpicyAnimation().fadeToUp(binding.nextBtn, 30F, 200)
                }

                1 -> {
                    binding.error.visibility = View.GONE
                    findNavController().navigate(R.id.enterNumbersFragment)
                }

                2 -> {
                    binding.error.visibility = View.GONE
                    findNavController().navigate(R.id.enterNamesFragment)
                }
            }
        }
    }

    fun initBackground() {
        isSelected = 0
        binding.choiceOne.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }

        binding.choiceTwo.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }

        binding.choiceThree.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }

        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_off)
        }
    }
}

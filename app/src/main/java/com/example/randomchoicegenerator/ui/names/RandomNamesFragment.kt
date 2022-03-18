package com.example.randomchoicegenerator.ui.names

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentRandomNamesBinding
import com.example.randomchoicegenerator.databinding.FragmentRandomNumberBinding
import com.example.randomchoicegenerator.model.IntFrom
import com.example.randomchoicegenerator.model.IntTo
import com.example.randomchoicegenerator.model.ListOfNames
import com.example.spicyanimation.SpicyAnimation
import kotlin.random.Random


class RandomNamesFragment : Fragment() {
    private lateinit var binding: FragmentRandomNamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_random_names,
            container,
            false
        )

        initComponent()
        return binding.root
    }

    private fun initComponent() {

        binding.animationSuccess.visibility = View.GONE
        binding.animationView.visibility = View.VISIBLE
        binding.randomName.visibility = View.GONE

        binding.animationView.playAnimation()

        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                // Do nothing
            }

            override fun onAnimationEnd(animation: Animator?) {
                // Do nothing
                initRandomChoice()
            }

            override fun onAnimationCancel(animation: Animator?) {
                // Do nothing
            }

            override fun onAnimationStart(animation: Animator?) {
                // Do nothing
            }
        })

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.enterNamesFragment)
        }

        binding.homeBtn.setOnClickListener {
            findNavController().navigate(R.id.typeChooseFragment)
        }
    }

    private fun initRandomChoice() {
        binding.renewCl.visibility = View.VISIBLE
        binding.animationView.visibility = View.GONE
        binding.randomName.visibility = View.VISIBLE

        binding.animationSuccess.visibility = View.VISIBLE
        binding.animationSuccess.playAnimation()

        SpicyAnimation().fadeToDown(binding.randomName, 50F, 1200)
        binding.randomName.text = ListOfNames.get(getRandomNumber()).label

        binding.renewCl.setOnClickListener {
            initComponent()
        }
    }

    private fun getRandomNumber() : Int {
        // The min parameter (the origin) is inclusive
        // whereas the upper bound max is exclusive.
        return Random.nextInt(0,ListOfNames.size!!.toInt())
    }

}
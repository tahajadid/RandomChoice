package com.example.randomchoicegenerator.ui.numbers

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentRandomNumberBinding
import com.example.randomchoicegenerator.model.IntFrom
import com.example.randomchoicegenerator.model.IntTo
import com.example.spicyanimation.SpicyAnimation
import kotlin.random.Random

class RandomNumberFragment : Fragment() {

    private lateinit var binding: FragmentRandomNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_random_number,
            container,
            false
        )

        initComponent()
        return binding.root
    }

    private fun initComponent() {

        binding.oneNumberCl.visibility = View.GONE
        binding.renewCl.visibility = View.GONE
        binding.twoNumberCl.visibility = View.GONE

        binding.animationView.visibility = View.VISIBLE
        binding.animationView.playAnimation()

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.enterNumbersFragment)
        }

        binding.homeBtn.setOnClickListener {
            findNavController().navigate(R.id.typeChooseFragment)
        }

        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                // Do nothing
            }

            override fun onAnimationEnd(animation: Animator?) {
                // Do nothing
                initRandomChoice()
                binding.homeBtn.isEnabled = true
                binding.nextBtn.isEnabled = true
            }

            override fun onAnimationCancel(animation: Animator?) {
                // Do nothing
            }

            override fun onAnimationStart(animation: Animator?) {
                // Do nothing
            }
        })
    }

    private fun initRandomChoice() {
        binding.animationView.visibility = View.GONE
        binding.renewCl.visibility = View.VISIBLE
        binding.oneNumberCl.visibility = View.GONE
        binding.twoNumberCl.visibility = View.GONE

        val numberFromRandom = getRandomNumber()


        if (numberFromRandom <10) {
            binding.oneNumberCl.visibility = View.VISIBLE
            matchChoiceAndAnimation(binding.numberOneAnimation, numberFromRandom)
        } else if ( (numberFromRandom<100) && (numberFromRandom>9)) {
            matchChoiceAndAnimation(binding.numberRightAnimation, getFirstNumber(numberFromRandom))
            matchChoiceAndAnimation(binding.numberLeftAnimation, getSecondNumber(numberFromRandom))
            binding.twoNumberCl.visibility = View.VISIBLE
        } else {
            // three numbers
        }

        binding.renewCl.setOnClickListener {
            initComponent()
        }
    }

    private fun getSecondNumber(numberFromRandom: Int): Int {
        return numberFromRandom / 10
    }

    private fun getFirstNumber(numberFromRandom: Int): Int {
        return numberFromRandom % 10
    }

    private fun getRandomNumber(): Int {
        // The min parameter (the origin) is inclusive
        // whereas the upper bound max is exclusive.
        return Random.nextInt(IntFrom!!.toInt()-1, IntTo!!.toInt())
    }

    private fun matchChoiceAndAnimation(lottieView: LottieAnimationView, nextValues: Int) {

        when (nextValues) {
            0 -> {
                lottieView.setAnimation(R.raw.number_zero)
            }
            1 -> {
                lottieView.setAnimation(R.raw.number_one)
            }
            2 -> {
                lottieView.setAnimation(R.raw.number_two)
            }
            3 -> {
                lottieView.setAnimation(R.raw.number_three)
            }
            4 -> {
                lottieView.setAnimation(R.raw.number_four)
            }
            5 -> {
                lottieView.setAnimation(R.raw.number_five)
            }
            6 -> {
                lottieView.setAnimation(R.raw.number_six)
            }
            7 -> {
                lottieView.setAnimation(R.raw.number_seven)
            }
            8 -> {
                lottieView.setAnimation(R.raw.number_eight)
            }
            9 -> {
                lottieView.setAnimation(R.raw.number_nine)
            }
        }

        SpicyAnimation().fadeToUp(binding.renewCl, 20F, 300)
        SpicyAnimation().fadeToUp(binding.numberOneAnimation, 50F, 800)

        lottieView.playAnimation()
    }
}

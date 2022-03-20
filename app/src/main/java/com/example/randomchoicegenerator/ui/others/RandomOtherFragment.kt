package com.example.randomchoicegenerator.ui.others

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentRandomNumberBinding
import com.example.randomchoicegenerator.databinding.FragmentRandomOtherBinding
import com.example.randomchoicegenerator.model.ListOfNames
import com.example.randomchoicegenerator.model.ListOfOthers
import com.example.randomchoicegenerator.model.SocialMediaSelected
import com.example.spicyanimation.SpicyAnimation
import com.google.android.gms.ads.AdRequest
import kotlin.random.Random


class RandomOtherFragment : Fragment() {
    private lateinit var binding: FragmentRandomOtherBinding

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
            R.layout.fragment_random_other,
            container,
            false
        )

        setBannerAds()
        initComponent()
        return binding.root
    }

    private fun setBannerAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun initComponent() {
        binding.animationSuccess.visibility = View.GONE
        binding.animationCongrate.visibility = View.GONE
        binding.randomName.visibility = View.GONE
        binding.randomCongrate.visibility = View.GONE

        binding.animationView.visibility = View.VISIBLE

        binding.animationView.playAnimation()

        setTheRightImage()
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.enterOthersFragment)
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

    private fun setTheRightImage() {
        when(SocialMediaSelected){
            "facebook" -> binding.imageCongrate.setImageResource(R.drawable.facebook)
            "insta" -> binding.imageCongrate.setImageResource(R.drawable.insta)
            "other" -> binding.imageCongrate.setImageResource(R.drawable.random_icon_last)
            "wtsp" -> binding.imageCongrate.setImageResource(R.drawable.whatsapp)
            "youtube" -> binding.imageCongrate.setImageResource(R.drawable.youtube)
            "telegram" -> binding.imageCongrate.setImageResource(R.drawable.telegram)
            "forms" -> binding.imageCongrate.setImageResource(R.drawable.google_forms)
            "school" -> binding.imageCongrate.setImageResource(R.drawable.graduation_last)

        }
    }

    private fun initRandomChoice() {
        binding.renewCl.visibility = View.VISIBLE
        binding.animationView.visibility = View.GONE
        binding.randomName.visibility = View.VISIBLE
        binding.randomCongrate.visibility = View.VISIBLE

        binding.animationSuccess.visibility = View.VISIBLE
        binding.animationSuccess.playAnimation()

        binding.animationCongrate.visibility = View.VISIBLE
        binding.animationCongrate.playAnimation()

        SpicyAnimation().fadeToDown(binding.randomName, 50F, 1200)
        binding.randomName.text = ListOfOthers.get(getRandomNumber()).label

        binding.renewCl.setOnClickListener {
            initComponent()
        }
    }

    private fun getRandomNumber() : Int {
        // The min parameter (the origin) is inclusive
        // whereas the upper bound max is exclusive.
        return Random.nextInt(0,ListOfOthers.size!!.toInt())
    }


}
package com.example.randomchoicegenerator.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentSplashScreenBinding
import com.example.spicyanimation.SpicyAnimation
import com.google.android.gms.ads.AdRequest

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

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
            R.layout.fragment_splash_screen,
            container,
            false
        )

        SpicyAnimation().fadeToUp(binding.titleBottom, 50F, 700)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            findNavController().navigate(R.id.typeChooseFragment)
        }, 3500) // 2500 is the delayed time in milliseconds.


        // Inflate the layout for this fragment
        return binding.root
    }
}

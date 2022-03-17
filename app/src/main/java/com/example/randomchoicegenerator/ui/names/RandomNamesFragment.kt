package com.example.randomchoicegenerator.ui.names

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
            R.layout.fragment_random_number,
            container,
            false
        )

        initComponent()
        return binding.root
    }

    private fun initComponent() {

        binding.animationView.visibility = View.VISIBLE
        binding.animationView.playAnimation()

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.enterNamesFragment)
        }

        binding.homeBtn.setOnClickListener {
            findNavController().navigate(R.id.typeChooseFragment)
        }
    }

}
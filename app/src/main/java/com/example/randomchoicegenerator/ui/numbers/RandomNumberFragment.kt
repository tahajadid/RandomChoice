package com.example.randomchoicegenerator.ui.numbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentEnterNumbersBinding
import com.example.randomchoicegenerator.databinding.FragmentRandomNumberBinding
import com.example.randomchoicegenerator.model.IntFrom
import com.example.randomchoicegenerator.model.IntTo
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
        val nextValues = Random.nextInt(IntFrom!!.toInt(), IntTo!!.toInt())

        Toast.makeText(
            context, " result : " + nextValues, Toast.LENGTH_LONG
        ).show()

        return binding.root
    }
}

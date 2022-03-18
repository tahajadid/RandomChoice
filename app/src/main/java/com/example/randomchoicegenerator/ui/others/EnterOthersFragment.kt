package com.example.randomchoicegenerator.ui.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentEnterNumbersBinding
import com.example.randomchoicegenerator.databinding.FragmentEnterOthersBinding
import com.example.spicyanimation.SpicyAnimation

class EnterOthersFragment : Fragment() {

    private lateinit var binding: FragmentEnterOthersBinding

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
            R.layout.fragment_enter_others,
            container,
            false
        )

        initComponent()

        return binding.root
    }

    private fun initComponent() {

        SpicyAnimation().fadeToDown(binding.view, 20F, 400)

        initAllBackgrounds()

        binding.viewFace.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewFace.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

        binding.viewWtsp.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewWtsp.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

        binding.viewYoutube.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewYoutube.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }

        }

        binding.viewTelegram.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewTelegram.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

        binding.viewForms.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewForms.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

        binding.viewInsta.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewInsta.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

        binding.viewSchool.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewSchool.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

        binding.viewOther.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewOther.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
        }

    }

    private fun enableNext() {
        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
        }
    }

    private fun initAllBackgrounds() {

        binding.viewFace.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewForms.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewInsta.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewOther.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewSchool.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewTelegram.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewYoutube.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }
        binding.viewWtsp.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.social_media_off)
        }

        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_off)
        }

    }
}

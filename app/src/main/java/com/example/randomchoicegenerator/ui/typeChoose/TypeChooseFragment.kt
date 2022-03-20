package com.example.randomchoicegenerator.ui.typeChoose

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentTypeChooseBinding
import com.example.randomchoicegenerator.model.ModelPreferencesManager
import com.example.spicyanimation.SpicyAnimation
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class TypeChooseFragment : Fragment() {

    private var interstitialAd: InterstitialAd? = null

    private lateinit var binding: FragmentTypeChooseBinding
    private var isSelected = 0
    private var isShowen = false

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

        // Ads
        setInterstitalAd()
        setBannerAds()

        SpicyAnimation().fadeToDown(binding.separatorTop, 20F, 400)
        initComponent()
        return binding.root
    }



    override fun onResume() {
        super.onResume()
    }



    private fun initComponent() {
        initBackground()

        /**
         * Verify if this is the first connexion
         */
        if (ModelPreferencesManager.get<Boolean>("firstConnexion") == null) {
            showDemoSteps()
            ModelPreferencesManager.put(true, "firstConnexion") // Raise true of user had already connected
        }

        SpicyAnimation().fadeToDown(binding.descriptionNumbers,20F,600)
        SpicyAnimation().fadeToDown(binding.descriptionNames,20F,800)
        SpicyAnimation().fadeToDown(binding.descriptionAutres,20F,1200)

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
            isSelected = 3
            binding.viewOther.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.choose_type_bg_selected)
            }

            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
            }
        }

        // Section next button
        binding.nextBtn.setOnClickListener {


            if(interstitialAd!=null){
                Log.d("LogAdsSee","if")
                interstitialAd?.show(requireActivity())
            }else{
                Log.d("LogAdsSee","else")
            }

            when (isSelected) {
                0 -> {
                    binding.error.visibility = View.VISIBLE
                    SpicyAnimation().fadeToDown(binding.nextBtn, 30F, 200)
                }
                1 -> {
                    binding.error.visibility = View.GONE
                    findNavController().navigate(R.id.enterNumbersFragment)
                }

                2 -> {
                    binding.error.visibility = View.GONE
                    findNavController().navigate(R.id.enterNamesFragment)
                }
                3 -> {
                    binding.error.visibility = View.GONE
                    findNavController().navigate(R.id.enterOthersFragment)
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

        binding.viewOther.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }

        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_off)
        }
    }

    private fun showDemoSteps() {
        initBackground()
        disableInteraction()

        binding.viewDemo.visibility = View.VISIBLE
        binding.textView2.elevation = 10F
        binding.imageView.elevation = 10F
        binding.separatorTop.elevation = 10F

        initViewDemo()
        showPartOne()
    }

    private fun disableInteraction() {
        binding.choiceOne.isEnabled = false
        binding.choiceTwo.isEnabled = false
        binding.choiceTwo.isEnabled = false

    }

    private fun enableInteraction() {
        binding.choiceOne.isEnabled = true
        binding.choiceTwo.isEnabled = true
        binding.choiceTwo.isEnabled = true
    }

    private fun showPartOne() {
        binding.textDemo1.visibility = View.VISIBLE
        binding.nextImage1.visibility = View.VISIBLE
        binding.choiceOne.elevation = 10F
        binding.choiceOne.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }

        binding.nextImage1.setOnClickListener {
            binding.choiceOne.elevation = 0F
            showPartTwo()
        }
    }

    private fun showPartTwo() {
        initBackground()
        initViewDemo()

        binding.choiceTwo.elevation = 10F
        binding.choiceTwo.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }
        binding.textDemo2.visibility = View.VISIBLE
        binding.nextImage2.visibility = View.VISIBLE

        binding.nextImage2.setOnClickListener {
            binding.choiceTwo.elevation = 0F
            showPartThree()
        }
    }

    private fun showPartThree() {
        initBackground()
        initViewDemo()

        binding.choiceThree.elevation = 10F
        binding.imageView5.elevation = 10F

        binding.choiceThree.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.choose_type_bg_unselected)
        }
        binding.textDemo3.visibility = View.VISIBLE
        binding.nextImage3.visibility = View.VISIBLE

        binding.nextImage3.setOnClickListener {
            binding.choiceThree.elevation = 0F
            binding.imageView5.elevation = 0F
            isShowen = true
            binding.descriptionAutres.visibility = View.VISIBLE
            binding.descriptionNames.visibility = View.VISIBLE
            binding.descriptionNumbers.visibility = View.VISIBLE

            binding.viewDemo.visibility = View.GONE
            binding.textDemo3.visibility = View.GONE
            binding.nextImage3.visibility = View.GONE

            binding.textView2.elevation = 0F
            binding.imageView.elevation = 0F
            binding.separatorTop.elevation = 0F

            initComponent()
            enableInteraction()
        }

    }

    fun initViewDemo(){
        binding.textDemo1.visibility = View.GONE
        binding.nextImage1.visibility = View.GONE
        binding.textDemo2.visibility = View.GONE
        binding.nextImage2.visibility = View.GONE
        binding.textDemo3.visibility = View.GONE
        binding.nextImage3.visibility = View.GONE

        binding.descriptionAutres.visibility = View.GONE
        binding.descriptionNames.visibility = View.GONE
        binding.descriptionNumbers.visibility = View.GONE

    }

    private fun setBannerAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun setInterstitalAd() {

        interstitialAd = null
        MobileAds.initialize(context)

        // Create a full screen content callback.
        val fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                interstitialAd = null
            }

        }

        InterstitialAd.load(
            context,
            "ca-app-pub-3940256099942544/1033173712",
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    interstitialAd?.fullScreenContentCallback = fullScreenContentCallback
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                }

            })
    }
}

package com.example.randomchoicegenerator.ui.others

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentEnterNumbersBinding
import com.example.randomchoicegenerator.databinding.FragmentEnterOthersBinding
import com.example.randomchoicegenerator.model.CustomObject
import com.example.randomchoicegenerator.model.ListOfNames
import com.example.randomchoicegenerator.model.ListOfOthers
import com.example.randomchoicegenerator.model.SocialMediaSelected
import com.example.spicyanimation.SpicyAnimation
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

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

        listenToChnages()

        binding.addOther.setOnClickListener {
            addNewChip()
        }

        binding.nextBtn.setOnClickListener {
            getItems()

            Log.d("ValueOfString","String : "+SocialMediaSelected)
            if(ListOfOthers.size==0){
                showError("Entrez des mots dans le champ pour choisir..")
            }else if(SocialMediaSelected=="noth"){
                showError("Choisisez la plateforme de compétition..")
            }else{
                binding.errorMessage.visibility = View.GONE
                findNavController().navigate(R.id.randomOtherFragment)
            }

        }
    }

    private fun showError(errorMessage :String) {
        binding.errorMessage.visibility = View.VISIBLE
        binding.errorMessage.text = errorMessage
        SpicyAnimation().fadeToUp(binding.nextBtn, 30F, 200)
    }

    /**
     * listen to social media click
     */
    private fun listenToChnages() {
        binding.viewFace.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewFace.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "facebook"
        }

        binding.viewWtsp.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewWtsp.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "wtsp"
        }

        binding.viewYoutube.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewYoutube.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "youtube"
        }

        binding.viewTelegram.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewTelegram.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "telegram"
        }

        binding.viewForms.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewForms.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "forms"
        }

        binding.viewInsta.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewInsta.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "insta"
        }

        binding.viewSchool.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewSchool.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "school"
        }

        binding.viewOther.setOnClickListener {
            initAllBackgrounds()
            enableNext()
            binding.viewOther.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.social_media_on)
            }
            SocialMediaSelected = "other"
        }
    }

    private fun enableNext() {
        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
        }
    }

    private fun initAllBackgrounds() {
        SocialMediaSelected = "noth"
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


    private fun addNewChip() {

        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
        }

        val keyword: String = binding.inputOther.text.toString()
        if (keyword == null || keyword.isEmpty()) {
            Toast.makeText(context, "Entrez un mot dans le champ..", Toast.LENGTH_LONG).show()
            return
        }

        try {
            val inflater = LayoutInflater.from(context)

            // Create a Chip from Layout.
            val newChip =
                inflater.inflate(R.layout.layout_chip_entry, binding.chipGroup, false) as Chip
            newChip.text = keyword
            newChip.setTextIsSelectable(false)
            //
            // Other methods:
            //
            // newChip.setCloseIconVisible(true);
            // newChip.setCloseIconResource(R.drawable.your_icon);
            // newChip.setChipIconResource(R.drawable.your_icon);
            // newChip.setChipBackgroundColorResource(R.color.red);
            // newChip.setTextAppearanceResource(R.style.ChipTextStyle);
            // newChip.setElevation(15);
            binding.chipGroup.addView(newChip)

            // Set Listener for the Chip:
            newChip.setOnCheckedChangeListener { buttonView, isChecked ->
                handleChipCheckChanged(
                    buttonView as Chip,
                    isChecked
                )
            }
            newChip.setOnCloseIconClickListener { v -> handleChipCloseIconClicked(v as Chip) }
            binding.inputOther.setText("")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_LONG).show()
        }

    }

    // User click on "Show Selections" button.
    private fun getItems() {
        val count: Int = binding.chipGroup.getChildCount()
        var s: String? = null
        for (i in 0 until count) {
            val child = binding.chipGroup.getChildAt(i) as Chip
            s += ", " + child.text.toString()
            if(child.text.toString()!=null){
                ListOfOthers.add(i, CustomObject(i,child.text.toString()))
            }

        }
        Log.d("ValueChoose","List is = "+ ListOfOthers.toString())
    }

    // User close a Chip.
    private fun handleChipCloseIconClicked(chip: Chip) {
        val parent = chip.parent as ChipGroup
        parent.removeView(chip)
    }

    // Chip Checked Changed
    private fun handleChipCheckChanged(chip: Chip, isChecked: Boolean) {}
}

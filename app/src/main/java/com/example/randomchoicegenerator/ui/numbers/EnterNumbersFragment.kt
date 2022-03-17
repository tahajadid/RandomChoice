package com.example.randomchoicegenerator.ui.numbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker.OnValueChangeListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentEnterNumbersBinding
import com.example.randomchoicegenerator.model.IntFrom
import com.example.randomchoicegenerator.model.IntTo
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.random.Random

// ktlint-disable no-wildcard-imports

class EnterNumbersFragment : Fragment() {

    private lateinit var binding: FragmentEnterNumbersBinding
    private var sectionSelected = 0
    private var pickerVals: Array<String> = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")
    private var numberChooseFrom: String = "0"
    private var numberChooseTo: String = "0"

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
            R.layout.fragment_enter_numbers,
            container,
            false
        )

        initComponent()

        return binding.root
    }

    private fun initComponent() {

        // add number
        binding.addNumber.setOnClickListener {
            addNewChip()
        }

        // first checkbox
        binding.checkBoxIntervalle.setOnClickListener {
            initBackground()
            sectionSelected = 1
            // change background
            binding.intervalCl.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.method_numbers_bg_on)
            }
            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
            }

            binding.checkBoxIntervalle.setImageResource(R.drawable.selected_radio)
            binding.checkBoxSpecific.setImageResource(R.drawable.unselected_radio)
        }

        // second checkbox
        binding.checkBoxSpecific.setOnClickListener {
            initBackground()
            sectionSelected = 2
            // change background
            binding.specialCl.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.method_numbers_bg_on)
            }
            binding.nextBtn.background = context?.let {
                ContextCompat.getDrawable(it, R.drawable.confirm_button_on)
            }

            binding.checkBoxSpecific.setImageResource(R.drawable.selected_radio)
            binding.checkBoxIntervalle.setImageResource(R.drawable.unselected_radio)
        }

        binding.numberPickerFrom.maxValue = pickerVals.size
        binding.numberPickerFrom.minValue = 1

        binding.numberPickerTo.maxValue = pickerVals.size
        binding.numberPickerTo.minValue = 1

        binding.numberPickerFrom.displayedValues = pickerVals
        binding.numberPickerTo.displayedValues = pickerVals

        binding.numberPickerFrom.setOnValueChangedListener(
            OnValueChangeListener { numberPicker, i, i1 ->
                numberChooseFrom = binding.numberPickerFrom.getValue().toString()
                IntFrom = binding.numberPickerFrom.getValue().toString()
            }
        )
        binding.numberPickerTo.setOnValueChangedListener(
            OnValueChangeListener { numberPicker, i, i1 ->
                numberChooseTo = binding.numberPickerTo.getValue().toString()
                IntTo = binding.numberPickerTo.getValue().toString()
            }
        )

        binding.nextBtn.setOnClickListener {
            if(IntFrom!!.toInt() > IntTo!!.toInt()){
                Toast.makeText(context,
                    "Le nombre est supérieure que "+ IntFrom, Toast.LENGTH_SHORT).show()
            }else{
                when (sectionSelected) {
                    0 -> {
                        Toast.makeText(context,
                            "S'il vous plait Choisissez une methode.. ", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        findNavController().navigate(R.id.randomNumberFragment)
                    }
                    2 -> {
                        findNavController().navigate(R.id.randomNumberFragment)
                    }
                }
            }

        }
    }

    private fun initBackground() {
        sectionSelected = 0
        binding.intervalCl.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.method_numbers_bg_off)
        }
        binding.specialCl.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.method_numbers_bg_off)
        }
        binding.nextBtn.background = context?.let {
            ContextCompat.getDrawable(it, R.drawable.confirm_button_off)
        }
    }

    private fun addNewChip() {
        val keyword: String = binding.inputNumber.text.toString()
        if (keyword == null || keyword.isEmpty()) {
            Toast.makeText(context, "Entrez un nombre..", Toast.LENGTH_LONG).show()
            return
        }

        try {
            val inflater = LayoutInflater.from(context)

            // Create a Chip from Layout.
            val newChip =
                inflater.inflate(R.layout.layout_chip_entry, binding.chipGroup, false) as Chip
            newChip.text = keyword

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
            binding.inputNumber.setText("")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    // User close a Chip.
    private fun handleChipCloseIconClicked(chip: Chip) {
        val parent = chip.parent as ChipGroup
        parent.removeView(chip)
    }

    // Chip Checked Changed
    private fun handleChipCheckChanged(chip: Chip, isChecked: Boolean) {}
}

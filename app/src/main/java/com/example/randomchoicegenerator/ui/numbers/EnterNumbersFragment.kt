package com.example.randomchoicegenerator.ui.numbers

import android.os.Bundle
import android.util.Log
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
import com.example.randomchoicegenerator.model.*
import com.example.randomchoicegenerator.model.IntFrom
import com.example.randomchoicegenerator.model.IntTo
import com.example.randomchoicegenerator.model.ListOfNumbers
import com.example.randomchoicegenerator.myArrayProvider
import com.example.spicyanimation.SpicyAnimation
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

// ktlint-disable no-wildcard-imports

class EnterNumbersFragment : Fragment() {

    private lateinit var binding: FragmentEnterNumbersBinding
    private var sectionSelected = 0
    private var pickerVals: Array<String> = arrayOf("0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "11", "12", "13", "14", "15","210","211","212","213")
    private var numberChooseFrom: String = "0"
    private var numberChooseTo: String = "0"
    private lateinit var arrayToPut : Array<String>

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

        /**
         * for test
         */

        val my = myArrayProvider(999)
        arrayToPut = my.getArrayList()
        Log.d("NumberChoose","all size =  "+my.getArrayList().size + " "+my.getArrayList()[0])

        initComponent()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initComponent()
    }

    private fun initComponent() {

        ListOfNumbers = ArrayList()

        SpicyAnimation().fadeToDown(binding.view, 20F, 400)

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

        binding.numberPickerFrom.maxValue = arrayToPut.size - 1
        binding.numberPickerFrom.minValue = 0

        binding.numberPickerTo.maxValue = arrayToPut.size - 1
        binding.numberPickerTo.minValue = 0

        binding.numberPickerFrom.displayedValues = arrayToPut
        binding.numberPickerTo.displayedValues = arrayToPut

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

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.typeChooseFragment)
        }

        binding.nextBtn.setOnClickListener {

            if(sectionSelected==0){
                showError("S'il vous plait, choisissez une methode..")
            }else{
                when (sectionSelected) {
                    1 -> {
                        if(IntFrom!!.toInt() > IntTo!!.toInt()){
                            showError("Le nombre "+ IntFrom.toString()+" est supérieure que "+ IntTo.toString())
                        }else{
                            binding.errorMessage.visibility = View.GONE
                            findNavController().navigate(R.id.randomNumberFragment)
                        }
                    }
                    2 -> {
                        getItems()
                        if (ListOfNumbers.size==0){
                            showError("S'il vout plait, entrez des numéros..")
                        }else{
                            binding.errorMessage.visibility = View.GONE
                            findNavController().navigate(R.id.randomNumberSpecificFragment)
                        }
                    }
                }
            }
        }
    }

    private fun showError(errorMessage :String) {
        binding.errorMessage.visibility = View.VISIBLE
        binding.errorMessage.text = errorMessage
        SpicyAnimation().fadeToUp(binding.nextBtn, 30F, 200)
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

    // User click on "Show Selections" button.
    private fun getItems() {
        val count: Int = binding.chipGroup.getChildCount()
        var s: String? = null
        for (i in 0 until count) {
            val child = binding.chipGroup.getChildAt(i) as Chip
            s += ", " + child.text.toString()
            if(child.text.toString()!=null){
                ListOfNumbers.add(i, CustomObject(i,child.text.toString()))
            }

        }
        Log.d("ValueChoose","List is = "+ ListOfNumbers.toString())
    }

    // User close a Chip.
    private fun handleChipCloseIconClicked(chip: Chip) {
        val parent = chip.parent as ChipGroup
        parent.removeView(chip)
    }

    // Chip Checked Changed
    private fun handleChipCheckChanged(chip: Chip, isChecked: Boolean) {}
}

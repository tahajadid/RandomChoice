package com.example.randomchoicegenerator.ui.names

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.randomchoicegenerator.R
import com.example.randomchoicegenerator.databinding.FragmentEnterNamesBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class EnterNamesFragment : Fragment() {

    private lateinit var binding: FragmentEnterNamesBinding

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
            R.layout.fragment_enter_names,
            container,
            false
        )

        initComponent()
        return binding.root
    }

    private fun initComponent() {
        binding.addName.setOnClickListener {
            addNewChip()
        }

        binding.nextBtn.setOnClickListener {
            // Do something..
        }
    }

    private fun addNewChip() {
        val keyword: String = binding.inputName.text.toString()
        if (keyword == null || keyword.isEmpty()) {
            Toast.makeText(context, "Entrez un nom..", Toast.LENGTH_LONG).show()
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
            binding.inputName.setText("")
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error: " + e.message, Toast.LENGTH_LONG).show()
        }

        getItems()
    }

    // User click on "Show Selections" button.
    private fun getItems() {
        val count: Int = binding.chipGroup.getChildCount()
        var s: String? = null
        for (i in 0 until count) {
            val child = binding.chipGroup.getChildAt(i) as Chip
            if (s != null) {
                s += ", " + child.text.toString()
            }
        }
        Toast.makeText(context, "result : " + s, Toast.LENGTH_LONG).show()
    }

    // User close a Chip.
    private fun handleChipCloseIconClicked(chip: Chip) {
        val parent = chip.parent as ChipGroup
        parent.removeView(chip)
    }

    // Chip Checked Changed
    private fun handleChipCheckChanged(chip: Chip, isChecked: Boolean) {}
}

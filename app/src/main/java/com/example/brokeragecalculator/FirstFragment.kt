package com.example.brokeragecalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.brokeragecalculator.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding= FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateButton.setOnClickListener {
            calculateBrokerage()
        }
    }

    private fun calculateBrokerage() {
        val amountString = binding.amountEditText.text.toString()
        val rateString = binding.brokerageRateEditText.text.toString()

        if (amountString.isNotEmpty() && rateString.isNotEmpty()) {
            val amount = amountString.toDouble()
            val rate = rateString.toDouble()
            val brokerage = (amount * rate) / 100

            binding.resultTextView.text = "Brokerage: ₹${String.format("%.2f", brokerage)}"
        } else {
            binding.resultTextView.text = "Please enter valid values."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.brokeragecalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.brokeragecalculator.databinding.FragmentBrokerageBinding

class BrokerageCalcFragment : Fragment() {

    private var _binding: FragmentBrokerageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrokerageBinding.inflate(inflater, container, false)
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
        val buyPriceString = binding.buyPriceEditText.text.toString()
        val sellPriceString = binding.sellPriceEditText.text.toString()

        if (amountString.isNotEmpty() && buyPriceString.isNotEmpty() && sellPriceString.isNotEmpty()) {
            val amount = amountString.toDouble()
            val buyPrice = buyPriceString.toDouble()
            val sellPrice = sellPriceString.toDouble()
            val totalCost = amount * buyPrice
            val totalRevenue = amount * sellPrice
            val brokerage = (totalCost * 0.01) // Assuming 1% brokerage for example

            binding.resultTextView.text = "Brokerage: ₹${String.format("%.2f", brokerage)}"
            binding.resultTextView.append("\nTotal Profit/Loss: ₹${String.format("%.2f", totalRevenue - totalCost - brokerage)}")
        } else {
            binding.resultTextView.text = "Please enter valid values."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

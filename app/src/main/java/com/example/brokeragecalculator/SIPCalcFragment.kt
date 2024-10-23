package com.example.yourapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.brokeragecalculator.R
import kotlin.math.pow

class SIPCalcFragment : Fragment() {

    private lateinit var sipAmountEditText: EditText
    private lateinit var annualReturnRateEditText: EditText
    private lateinit var investmentDurationEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sip_calc, container, false)

        sipAmountEditText = view.findViewById(R.id.sip_amount)
        annualReturnRateEditText = view.findViewById(R.id.annual_return_rate)
        investmentDurationEditText = view.findViewById(R.id.investment_duration)
        calculateButton = view.findViewById(R.id.calculate_button)
        resultTextView = view.findViewById(R.id.result)

        calculateButton.setOnClickListener {
            calculateSIP()
        }

        return view
    }

    private fun calculateSIP() {
        val sipAmount = sipAmountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val annualReturnRate = annualReturnRateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val investmentDuration = investmentDurationEditText.text.toString().toIntOrNull() ?: 0

        if (sipAmount > 0 && annualReturnRate > 0 && investmentDuration > 0) {
            val monthlyReturnRate = annualReturnRate / 12 / 100
            val totalMonths = investmentDuration * 12
            val futureValue = sipAmount * ((1 + monthlyReturnRate).pow(totalMonths) - 1) / monthlyReturnRate * (1 + monthlyReturnRate)

            resultTextView.text = "Future Value: ₹${String.format("%.2f", futureValue)}"
        } else {
            resultTextView.text = "Please enter valid values."
        }
    }
}

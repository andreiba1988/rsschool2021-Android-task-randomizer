package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minValue: EditText
    private lateinit var maxValue: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener(View.OnClickListener {
            if (minValue.text.toString().isEmpty() || maxValue.text.toString().isEmpty()) {
                Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                val min = minValue.text.toString().toLong()
                val max = maxValue.text.toString().toLong()
                if (max > Int.MAX_VALUE) {
                    Toast.makeText(
                        activity,
                        "Неправильный диапазон. Выберите что-то из этого [0, 2147483647]",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (min > max) {
                    Toast.makeText(
                        activity,
                        "Минимальное значение не должно быть больше максимального",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    (activity as OpenFragment).openSecondFragment(min.toInt(), max.toInt())
                }
            }
        })
        activity?.onBackPressedDispatcher?.addCallback {
            activity?.finish()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}

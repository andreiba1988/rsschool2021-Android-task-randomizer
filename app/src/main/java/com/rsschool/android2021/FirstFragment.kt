package com.rsschool.android2021

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: PressGenerate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as PressGenerate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        val editMin: EditText? = view.findViewById(R.id.min_value)
        val editMax: EditText? = view.findViewById(R.id.max_value)

        var min = -1
        var max = -1

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        generateButton?.setOnClickListener {
            if (editMin?.text.toString() != "" && editMax?.text.toString() != ""
                && editMin?.text.toString().toLong() <= Int.MAX_VALUE && editMax?.text.toString()
                    .toLong() <= Int.MAX_VALUE
            ) {
                min = editMin?.text.toString().toInt()
                max = editMax?.text.toString().toInt()
            }
            if (min in 0 until max && max > 0) {
                listener?.onGenerateButtonPressed(min, max)

            } else {
                Toast.makeText(activity, "Incorrect input!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    interface PressGenerate {
        fun onGenerateButtonPressed(min: Int, max: Int) {
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

package com.example.lab1.fragments

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lab1.R

class OutputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_output, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val editTextInput = arguments?.getString("editTextInput")
        val sansSerifButtonChecked = arguments?.getBoolean("sansSerifButtonChecked")
        val monospaceButtonChecked = arguments?.getBoolean("monospaceButtonChecked")
        val serifButtonChecked = arguments?.getBoolean("serifButtonChecked")

        val textView: TextView = view?.findViewById(R.id.textView) as TextView

        textView.text = editTextInput

        if (sansSerifButtonChecked == true) {
            textView.typeface = Typeface.SANS_SERIF
        } else if (monospaceButtonChecked == true) {
            textView.typeface = Typeface.MONOSPACE
        } else if (serifButtonChecked == true) {
            textView.typeface = Typeface.SERIF
        }
    }

}
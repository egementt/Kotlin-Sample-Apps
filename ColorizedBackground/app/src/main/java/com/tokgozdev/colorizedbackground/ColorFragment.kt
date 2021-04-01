package com.tokgozdev.colorizedbackground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_color.view.*

// TODO: Rename parameter arguments, choose names that match


class ColorFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_color, container, false)

        view.redButton.setOnClickListener {
            activity?.mainLayout?.setBackgroundColor(resources.getColor(R.color.red))
        }
        view.orangeButton.setOnClickListener {
            activity?.mainLayout?.setBackgroundColor(resources.getColor(R.color.orange))
        }
        view.blueButton.setOnClickListener {
            activity?.mainLayout?.setBackgroundColor(resources.getColor(R.color.blue))
        }
        view.blackButton.setOnClickListener {
            activity?.mainLayout?.setBackgroundColor(resources.getColor(R.color.black))
        }
        view.purpleButton.setOnClickListener {
            activity?.mainLayout?.setBackgroundColor(resources.getColor(R.color.purple_700))
        }
        view.greenButton.setOnClickListener {
            activity?.mainLayout?.setBackgroundColor(resources.getColor(R.color.teal_700))
        }

        return view
    }


}
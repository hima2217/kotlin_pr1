package com.example.kotlin_pr1.UI.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.kotlin_pr1.R

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val buttonToFragment1 = view.findViewById<Button>(R.id.button)

        buttonToFragment1.setOnClickListener {
            //findNavController().navigate(R.id.action_fragment3_to_fragment1)

            System.exit(-1)
        }

        return view
    }
}
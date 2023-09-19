package com.example.kotlin_pr1.UI.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.kotlin_pr1.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Переход к Фрагменту 2 с помощью
        // ручного управление транзакциями фрагментов
        val buttonToFragment2 = view.findViewById<Button>(R.id.button_to_fragment2)

        buttonToFragment2.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_firstFragment)

        }

        // Переход к Фрагменту 3 с помощью Navigation API
        val buttonToFragment3 = view.findViewById<Button>(R.id.button_to_fragment3)

        buttonToFragment3.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        return view
    }
}
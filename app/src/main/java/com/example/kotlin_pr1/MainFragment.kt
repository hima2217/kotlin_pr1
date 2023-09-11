package com.example.kotlin_pr1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Переход к Фрагменту 2 с помощью
        // ручного управление транзакциями фрагментов
        val buttonToFragment2 = view.findViewById<Button>(R.id.FirstButton)

        buttonToFragment2.setOnClickListener {
            val fragment2 = FirstFragment()
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment,fragment2)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction?.commit()
        }

        // Переход к Фрагменту 3 с помощью Navigation API
        val buttonToFragment3 = view.findViewById<Button>(R.id.SecondButton)

        buttonToFragment3.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        return view
    }
}
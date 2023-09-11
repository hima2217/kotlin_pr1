package com.example.kotlin_pr1.UI.View

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kotlin_pr1.R

class FirstFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val buttonToFragment1 = view.findViewById<Button>(R.id.button2)

        buttonToFragment1.setOnClickListener {
//            val fragmentTransaction = parentFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_container, Fragment1())
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//            fragmentTransaction.commit()

            System.exit(-1)
        }

        return view
    }
}
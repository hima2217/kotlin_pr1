package com.example.kotlin_pr1.UI.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_pr1.R
import com.example.kotlin_pr1.UI.StateHolder.Adapters.AdapterPhotolst
import com.example.kotlin_pr1.databinding.FragmentSecondBinding
import java.io.File

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: AdapterPhotolst
    private val FILENAME = "Date.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = AdapterPhotolst()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val buttonToFragment1 = view.findViewById<Button>(R.id.button3)

        buttonToFragment1.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_mainFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPhotoList()
    }

    private fun loadPhotoList() {
        val picturesDir = File(requireContext().getExternalFilesDir(null), "Pictures/CameraX-Image")
        val file = File(picturesDir, FILENAME)

        if (file.exists()) {
            val lines = file.readLines()
            adapter.submitList(lines)
        }
    }
}
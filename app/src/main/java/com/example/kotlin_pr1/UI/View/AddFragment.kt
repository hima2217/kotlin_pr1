package com.example.kotlin_pr1.UI.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin_pr1.Data.DataSource.Product.Product
import com.example.kotlin_pr1.UI.StateHolder.ViewModel.ProductVM
import com.example.kotlin_pr1.databinding.AddFragmentBinding

class AddFragment : Fragment() {
    //    private lateinit var binding: AddFragmentBinding
//    private lateinit var mHardDiskVM: HardDiskDriverVM;
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = AddFragmentBinding.inflate(inflater, container, false)
//
//        mHardDiskVM = ViewModelProvider(this).get(HardDiskDriverVM::class.java)
//
//        binding.add.setOnClickListener {
//            var tempName = binding.editText.text.toString()
//            if (tempName.isEmpty()) {
//                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
//            } else {
//                var item = HardDiskDriver(0, tempName, "a")
//                mHardDiskVM.addItem(item)
//                findNavController().popBackStack()
//            }
//
//        }
//
//
//        return binding.root
//    }
    private lateinit var binding: AddFragmentBinding
    private lateinit var mProductVM: ProductVM;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddFragmentBinding.inflate(inflater, container, false)

        mProductVM = ViewModelProvider(this).get(ProductVM::class.java)

        binding.add.setOnClickListener {
            var tempName = binding.editText.text.toString()
            if (tempName.isEmpty()) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            } else {
                var item = Product(0, tempName)
                mProductVM.addItem(item)
                findNavController().popBackStack()
            }

        }
        return binding.root
    }
}
package com.generation.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.generation.todo.databinding.FragmentFormBinding
import com.generation.todo.databinding.FragmentListBinding


class FormFragment : Fragment() {
    private lateinit var binding: FragmentFormBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater, container,false)

        // Inflate the layout for this fragment
        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment2_to_listFragment)
        }
        return binding.root
    }


}

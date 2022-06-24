package com.generation.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.todo.adapter.TarefaAdapter
import com.generation.todo.adapter.TaskClickListener
import com.generation.todo.databinding.ActivityMainBinding
import com.generation.todo.databinding.FragmentListBinding
import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment(), TaskClickListener {

    private lateinit var binding : FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentListBinding.inflate(layoutInflater,container,false)
        mainViewModel.listTarefa()

        // Configuração do RecyclerView
        val adapter =TarefaAdapter(this, mainViewModel, requireContext())
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)


        binding.floatingAdd.setOnClickListener{
            mainViewModel.tarefaSeleciona = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment2)
        }

        mainViewModel.myTarefaResponse.observe(viewLifecycleOwner){
            response -> if(response.body() != null){
                adapter.setList(response.body()!!)
        }
        }

        return binding.root
    }

    override fun onTaksClickListener(tarefa: Tarefa) {
        mainViewModel.tarefaSeleciona = tarefa
        findNavController().navigate(R.id.action_listFragment_to_formFragment2)
    }
}
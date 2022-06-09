package com.generation.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.todo.adapter.TarefaAdapter
import com.generation.todo.databinding.FragmentListBinding
import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

    private lateinit var binding : FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentListBinding.inflate(layoutInflater,container,false)


        var listTarefas = listOf(
            Tarefa(1,
                "Lavar a louça",
                "Lavar a louça do café e janta",
                "Raian",
                "09/06/2022",
                true,


            ),
            Tarefa(
            ),
            Tarefa(
            )
        )

        // Configuração do RecyclerView
        val adapter =TarefaAdapter()
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)

        adapter.setList(listTarefas)

        binding.floatingAdd.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_formFragment2)
        }
        return binding.root
    }
}
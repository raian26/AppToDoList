package com.generation.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.todo.adapter.TarefaAdapter
import com.generation.todo.databinding.FragmentListBinding
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
            Tarefa("Lavar a louça",
                "Lavar a louça do café e do jantar",
                "Raian",
                "2022/06/07",
                true,
                "Dia a dia"
            ),
            Tarefa("Regar as plantas",
                "regar as plantas da casa",
                "Raian",
                "2022/06/10",
                false,
                "Cuidados da casa"
            ),
            Tarefa("Skate",
                "Andar de skate na parte da manhã",
                "Raian",
                "2022/06/11",
                false,
                "Esporte e lazer"
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
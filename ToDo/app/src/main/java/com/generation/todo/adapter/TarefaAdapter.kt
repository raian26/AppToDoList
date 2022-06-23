package com.generation.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.generation.todo.MainViewModel
import com.generation.todo.databinding.CardLayoutBinding
import com.generation.todo.model.Tarefa

class TarefaAdapter(
    val taskClickListener: TaskClickListener,
    val mainViewModel: MainViewModel
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    class TarefaViewHolder(var binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

   private var listTarefa = emptyList<Tarefa>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listTarefa[position]

        holder.binding.textNome.text = tarefa.nome
        holder.binding.textDescricao.text = tarefa.descricao
        holder.binding.textResponsavel.text = tarefa.responsavel
        holder.binding.textData.text = tarefa.data
        holder.binding.switchAtivo.isChecked = tarefa.status
        holder.binding.textCategoria.text = tarefa.categoria.descricao

        holder.itemView.setOnClickListener{
            taskClickListener.onTaksClickListener(tarefa)
        }
        holder.binding.switchAtivo
            .setOnCheckedChangeListener{ compoundButton, ativo->
                tarefa.status = ativo
                mainViewModel.updateTarefa(tarefa)

            }
    }

    override fun getItemCount(): Int {
        return listTarefa.size
    }

    fun setList(list: List<Tarefa>){
        listTarefa = list.sortedByDescending { it.id }
        notifyDataSetChanged()

    }

}
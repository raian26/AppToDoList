package com.generation.todo.api

import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import retrofit2.Response

class Repository {
    suspend fun listCategoria() : Response<List<Categoria>>{
        return RetrofitInstance.api.listCategoria()
    }

    suspend fun addTarefa(tarefa: Tarefa) : Response<Tarefa>{
        return RetrofitInstance.api.addTarefa(tarefa)
    }

    suspend fun listTarefa(): Response<List<Tarefa>>{
        return  RetrofitInstance.api.listTarefa()
    }

    suspend fun updateTarefa(tarefa:Tarefa):Response<Tarefa>{
        return  RetrofitInstance.api.updateTarefa(tarefa)
    }
    suspend fun deleteTarefa(id:Long): Response<Tarefa>{
        return RetrofitInstance.api.deleteTarefa(id)
    }
}

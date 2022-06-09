package com.generation.todo.api

import com.generation.todo.model.Categoria
import retrofit2.Response

class Repository {
    suspend fun listCategoria() : Response<List<Categoria>>{
        return RetrofitInstance.api.listCategoria()
    }
}
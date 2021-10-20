package com.gruppe.marvel

import com.gruppe.unifique.retrofit.RetrofitClient
import com.gruppe.unifique.retrofit.model.Resultado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainRepository() {

    suspend fun getAllFilmes() = withContext(Dispatchers.IO) {
        try {
            val repo = RetrofitClient.service.getAllFilmes()
            if (repo.isSuccessful) {
                Resultado.Sucesso(dado = repo.body())
            } else {
                Resultado.Erro(Exception(repo.message()))
            }
        } catch (e: Exception) {
            Resultado.Erro(exception = e)
        }
    }
}

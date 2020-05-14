package br.com.levpatrim.model.repositories

import androidx.lifecycle.LiveData
import br.com.levpatrim.model.db.AppDatabase
import br.com.levpatrim.model.db.entities.Conta
import br.com.levpatrim.model.db.entities.ContaDao

//class ContaRepository(private val dao: Dao)
class ContaRepository(private val db: AppDatabase)
{
    val allContas: LiveData<List<Conta>> = db.getContaDao().getContas()

    suspend fun insert(conta: Conta) {
        db.getContaDao().insert(conta)
    }

    suspend fun delete(deleteConta : String)
    {
        db.getContaDao().deleteConta(deleteConta)
    }

    suspend fun deleteAll()
    {
        db.getContaDao().deleteAll()
    }

}
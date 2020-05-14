package br.com.levpatrim.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.levpatrim.model.db.AppDatabase
import br.com.levpatrim.model.db.entities.Conta
import br.com.levpatrim.model.repositories.ContaRepository
import kotlinx.coroutines.launch

class ContaViewModel(private val repository: ContaRepository) : ViewModel() {

    // LiveData gives us updated contas when they change.
    val allContas: LiveData<List<Conta>>

    init {
       //val dao = DatabaseService.getDatabase(application).Dao()

       // val dao = AppDatabase.invoke(context: Context)
      //  repository = ContaRepository(dao)
        allContas = repository.allContas
    }

    fun insert(conta : Conta)
    {
        viewModelScope.launch {
            repository.insert(conta)
        }
    }

    fun delete(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun deleteWord(deleteConta : String){
        viewModelScope.launch {
            repository.delete(deleteConta)
        }
    }
}

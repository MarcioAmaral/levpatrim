package br.com.levpatrim.view.conta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.levpatrim.model.repositories.ContaRepository
import br.com.levpatrim.viewmodel.ContaViewModel

class ContaViewModelFactory (private val repository: ContaRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContaViewModel(repository) as T
    }

}
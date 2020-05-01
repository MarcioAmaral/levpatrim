package br.com.levpatrim.view.home.quotes

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.levpatrim.model.repositories.QuotesRepository
import br.com.levpatrim.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred() {
        Log.d("ERROR 04","Antes by lazyDeferred()")
        repository.getQuotes()

    }
}

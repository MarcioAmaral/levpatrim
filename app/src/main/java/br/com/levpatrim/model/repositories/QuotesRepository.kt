package br.com.levpatrim.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.levpatrim.model.db.AppDatabase
import br.com.levpatrim.model.db.entities.Quote
import br.com.levpatrim.model.network.MyApi
import br.com.levpatrim.model.network.SafeApiRequest
import br.com.levpatrim.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository (
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            Log.d("ERROR 09","Antes saveQuotes(it) ")
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>>{
        Log.d("ERROR 05","Antes return withContext(Dispatchers.IO")
        return withContext(Dispatchers.IO){
            fetchQuotes()
            Log.d("ERROR 08","Antes db.getQuoteDao().getQuotes()")
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        Log.d("ERROR 06","Antes if (isFetchNeed()")
        if (isFetchNeed()) {
            Log.d("ERROR 07","Depois if (isFetchNeed()")
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeed(): Boolean {
        return true
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Log.d("ERROR 10","Antes Coroutines.io ")
        Coroutines.io {
            Log.d("ERROR 11","Antes db.getQuoteDao() ")
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

}
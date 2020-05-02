package br.com.levpatrim.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.levpatrim.model.db.AppDatabase
import br.com.levpatrim.model.db.entities.Quote
import br.com.levpatrim.model.network.MyApi
import br.com.levpatrim.model.network.SafeApiRequest
import br.com.levpatrim.model.preferences.PreferenceProvider
import br.com.levpatrim.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class QuotesRepository (
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
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
        val lastSavedAt = prefs.getLastSaveAt()

        Log.d("ERROR 06","Antes if (isFetchNeed()")
        if (lastSavedAt == null || isFetchNeed(LocalDateTime.parse(lastSavedAt))) {
            Log.d("ERROR 07","Depois if (isFetchNeed()")
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeed(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Log.d("ERROR 10","Antes Coroutines.io ")
        Coroutines.io {
            Log.d("ERROR 11","Antes db.getQuoteDao() ")
            db.getQuoteDao().saveAllQuotes(quotes)
            prefs.savelastSavedAt(LocalDateTime.now().toString())
        }
    }

}
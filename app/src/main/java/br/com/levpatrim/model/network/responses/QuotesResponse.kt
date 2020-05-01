package br.com.levpatrim.model.network.responses

import br.com.levpatrim.model.db.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)
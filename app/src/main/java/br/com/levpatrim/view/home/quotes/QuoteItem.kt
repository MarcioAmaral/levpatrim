package br.com.levpatrim.view.home.quotes

import br.com.levpatrim.R
import br.com.levpatrim.databinding.ItemQuoteBinding
import br.com.levpatrim.model.db.entities.Quote
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}
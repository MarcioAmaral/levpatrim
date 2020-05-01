package br.com.levpatrim.view.home.quotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.levpatrim.R
import br.com.levpatrim.util.Coroutines
import br.com.levpatrim.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ERROR 01","Antes inflater.inflate(R.layout.quotes_fragment")
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("ERROR 02","Antes ViewModelProviders")
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        Coroutines.main {
            Log.d("ERROR 03","Antes LiveData ${viewModel.quotes}")
            val quotes = viewModel.quotes.await()
            Log.d("ERROR 12","Depois LiveData ${quotes.value?.size}")
            quotes.observe(this, Observer {
                Log.d("ERROR 13","Antes it.size.toString()")
                context?.toast(it.size.toString())
            })
        }
    }
}

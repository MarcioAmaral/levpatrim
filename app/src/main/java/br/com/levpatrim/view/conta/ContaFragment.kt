package br.com.levpatrim.view.conta

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.levpatrim.R
import br.com.levpatrim.viewmodel.ContaViewModel


class ContaFragment : Fragment() {

    companion object {
        fun newInstance() = ContaFragment()
    }

    private lateinit var viewModel: ContaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.activity_conta, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

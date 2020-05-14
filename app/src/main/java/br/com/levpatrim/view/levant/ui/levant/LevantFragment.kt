package br.com.levpatrim.view.levant.ui.levant

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.levpatrim.R


class LevantFragment : Fragment() {

    companion object {
        fun newInstance() = LevantFragment()
    }

    private lateinit var viewModel: LevantViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.levant_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LevantViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

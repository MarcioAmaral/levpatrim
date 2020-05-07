package br.com.levpatrim.view.home.user

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import br.com.levpatrim.R
import br.com.levpatrim.databinding.ScreenUserFragmentBinding
import br.com.levpatrim.util.Coroutines
import br.com.levpatrim.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ScreenUser : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: ScreenUserViewModelFactory by instance()

    private lateinit var viewModel: ScreenUserViewModel

    private lateinit var dataBinding: ScreenUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      //  return inflater.inflate(R.layout.screen_user_fragment, container, false)

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.screen_user_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ScreenUserViewModel::class.java)
        Log.d("INFO","${viewModel.user.toString()}")


        Coroutines.main {
            val users = viewModel.user.await()
            users.observe(this, Observer {
                Log.d("INFO","${it.name.toString()}")
                Log.d("INFO","${it.email.toString()}")
                Log.d("INFO","${it.password.toString()}")
                Log.d("INFO","${it.updated_at.toString()}")
                dataBinding.nome.text = it.name.toString()
                dataBinding.email.text = it.email.toString()
                dataBinding.senha.text = it.password.toString()
            })
        }
    }
}




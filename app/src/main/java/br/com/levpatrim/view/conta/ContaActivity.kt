package br.com.levpatrim.view.conta

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.levpatrim.R
import br.com.levpatrim.model.db.entities.Conta
import br.com.levpatrim.view.auth.AuthViewModelFactory
import br.com.levpatrim.viewmodel.ContaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ContaActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : ContaViewModelFactory by instance()

    private val newContaActivityRequestCode = 1

    private lateinit var contaViewModel: ContaViewModel
    private lateinit var mdialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conta)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContaFragment.newInstance())
                .commitNow()
        }*/
        Log.d("ERROR","Antes viewModel")  //Problema na injeção de dependependência
        val viewModel = ViewModelProviders.of(this, factory).get(ContaViewModel::class.java)
        Log.d("ERROR","Antes recyclerView")
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecyclerViewAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        contaViewModel = ViewModelProvider(this).get(ContaViewModel::class.java)
        contaViewModel.allContas.observe(this, Observer { contas ->
            // Update the cached copy of the contas in the adapter.
            contas?.let { adapter.setWords(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(ContaActivity@ this, AllContaActivity::class.java)
            startActivityForResult(intent, newContaActivityRequestCode)
        }

        /*materialButtonDelete.setOnClickListener {
            contaViewModel.delete()
        }

        materialButtonDeleteWord.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this@ContaActivity)
            val dialogView = layoutInflater.inflate(R.layout.delete_dialog, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setPositiveButton("Submit") { dialogInterface, i ->
                contaViewModel.deleteWord(dialogView.etView.text.toString())
            }
            val b = dialogBuilder.create()
            b.show()

        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newContaActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AllContaActivity.EXTRA_REPLY)?.let {
                val conta = Conta(it)
                contaViewModel.insert(conta)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "empty",
                Toast.LENGTH_LONG).show()
        }
    }
}

package br.com.levpatrim.view.levant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import br.com.levpatrim.R
import br.com.levpatrim.view.levant.ui.levant.LevantFragment
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import kotlinx.android.synthetic.main.levant_activity.*

class LevantActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.levant_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LevantFragment.newInstance())
                .commitNow()
        }*/
        setSupportActionBar(toolbar)

        sppinerLoadConta()
        sppinerLoadCusto()
        sppinerLoadLocal()
        sppinerLoadPlanta()
        sppinerLoadEspecie()
        sppinerLoadLinha()
        sppinerLoadUnidade()
    }

    val searchablespinner_list: MutableList<String> = ArrayList()

    //val searchablespinner = findViewById(R.id.spinnerConta) as SearchableSpinner

    /*searchablespinner.setPositiveButton("Voltar");

    searchablespinner_list.add("Samsung");
    searchablespinner_list.add("Nokia");

    searchablespinner.setAdapter(
    ArrayAdapter<String>(
    this, android.R.layout.simple_spinner_dropdown_item,
    searchablespinner_list)
    )*/

    fun complTela1(view: View) {

        val intent = Intent(this, LevantComplActivity::class.java)
        startActivity(intent)
    }

    fun sppinerLoadConta() {
        //String array.
        val myStrings = arrayOf("001 - Máquinas", "068 - Equipamentos", "010 - Veículos", "015 - Móveis Utensílios")
        //Adapter for spinner
        spinnerConta.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
    fun sppinerLoadCusto() {
        //String array.
        val myStrings = arrayOf("001 - Geral", "012 - Administr.", "099 - Gerência", "020 - Vendas")
        //Adapter for spinner
        spinnerCusto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
    fun sppinerLoadLocal() {
        //String array.
        val myStrings = arrayOf("037 - Matriz", "065 - Filial 1", "099 - Viamão", "003 - Porto Alegre")
        //Adapter for spinner
        spinnerLocal.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
    fun sppinerLoadPlanta() {
        //String array.
        val myStrings = arrayOf("064 - Planta 1", "019 - Planta 4")
        //Adapter for spinner
        spinnerPlanta.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
    fun sppinerLoadEspecie() {
        //String array.
        val myStrings = arrayOf("016 - cadeira", "080 - suporte", "080 - robo", "070 - mesa")
        //Adapter for spinner
        spinnerEspecie.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
    fun sppinerLoadLinha() {
        //String array.
        val myStrings = arrayOf("001 - montagem", "026 - pintura", "029 - revisão")
        //Adapter for spinner
        spinnerLinha.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
    fun sppinerLoadUnidade() {
        //String array.
        val myStrings = arrayOf("001 - comércio", "010 - agrícula", "019 - construção")
        //Adapter for spinner
        spinnerUnidade.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, myStrings)
    }
}

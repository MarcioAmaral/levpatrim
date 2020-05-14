package br.com.levpatrim.view.levant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.levpatrim.R

class LevantComplActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levant_compl)
    }

    fun complTela2(view: View) {

        val intent = Intent(this, LevantCompl01Activity::class.java)
        startActivity(intent)
    }
}

package br.com.levpatrim.view.conta

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import br.com.levpatrim.R
import kotlinx.android.synthetic.main.conta_activity2.*

class AllContaActivity : AppCompatActivity() {

    companion object
    {
        const val EXTRA_REPLY = "br.com.levpatrim"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conta_activity2)

        button.setOnClickListener {
            textView2.text = textview.text!!.substring(0,1).capitalize()

            val replyIntent = Intent()
            if (TextUtils.isEmpty(textview.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = textview.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }
}
package br.com.levpatrim.view.conta

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.levpatrim.R
import br.com.levpatrim.model.db.entities.Conta
import java.util.*

class RecyclerViewAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ContaViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contas = emptyList<Conta>() // Cached copy of contas

    inner class ContaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contaItemView: TextView = itemView.findViewById(R.id.textView)
        val circleText : TextView = itemView.findViewById(R.id.tc_circle)
        val circle : CardView = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContaViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ContaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContaViewHolder, position: Int) {
        val current = contas[position]
        holder.contaItemView.text = current.descrConta
        holder.circleText.text = holder.contaItemView.text.substring(0,1).capitalize()
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.circle.setBackgroundColor(color)
    }

    internal fun setWords(contas: List<Conta>) {
        this.contas = contas
        notifyDataSetChanged()
    }

    override fun getItemCount() = contas.size
}

package br.com.levpatrim.model.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Conta(
    @PrimaryKey
    val contaId: String,
    val descrConta: String? = null
)
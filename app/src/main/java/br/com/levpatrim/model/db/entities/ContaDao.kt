package br.com.levpatrim.model.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContaDao {

    @Query("Select * from conta ORDER BY contaId ASC")
    fun getContas(): LiveData<List<Conta>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(conta : Conta)

    @Query("DELETE FROM Conta WHERE contaId = :deleteConta")
    suspend fun deleteConta(deleteConta : String)


    @Query("DELETE FROM Conta")
    suspend fun deleteAll()
}
package br.com.levpatrim.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.levpatrim.model.db.entities.Quote
import br.com.levpatrim.model.db.entities.User
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Database(
    entities = [User::class, Quote::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao
    abstract fun getQuoteDao() : QuoteDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}
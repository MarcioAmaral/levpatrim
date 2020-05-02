package br.com.levpatrim

import android.app.Application
import br.com.levpatrim.model.db.AppDatabase
import br.com.levpatrim.model.network.MyApi
import br.com.levpatrim.model.network.NetworkConnectionInterceptor
import br.com.levpatrim.model.preferences.PreferenceProvider
import br.com.levpatrim.model.repositories.QuotesRepository
import br.com.levpatrim.model.repositories.UserRepository
import br.com.levpatrim.view.auth.AuthViewModelFactory
import br.com.levpatrim.view.home.profile.ProfileViewModelFactory
import br.com.levpatrim.view.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton {
            NetworkConnectionInterceptor(
                instance()
            )
        }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance())  }
        bind() from provider { ProfileViewModelFactory(instance())  }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }
}
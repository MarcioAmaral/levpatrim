package br.com.levpatrim.model.network.responses

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import br.com.levpatrim.util.NoInternetException
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetException("Make sure have an active data connection")
        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable() : Boolean{

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }

}
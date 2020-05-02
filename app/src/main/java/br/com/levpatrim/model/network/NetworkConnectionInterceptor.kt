package br.com.levpatrim.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import br.com.levpatrim.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetException("Make sure have an active data connection")
        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable() : Boolean{
        var result = false
        val connecctivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connecctivityManager?.let {
            it.getNetworkCapabilities(connecctivityManager.activeNetwork).apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }


}
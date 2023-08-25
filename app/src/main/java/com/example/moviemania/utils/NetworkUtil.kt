package com.example.moviemania.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData

object NetworkUtil : ConnectivityManager.NetworkCallback() {

    private val networkLiveData : MutableLiveData<Boolean> = MutableLiveData()


    fun getNetworkLiveData(context : Context) : MutableLiveData<Boolean> {

        networkLiveData.postValue(false)

        //connectivity manager instance

        val connectivityManager  =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), this)
        }*/

        var isConnected = false

        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)

            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true
                    return@forEach
                }
            }
        }


        networkLiveData.postValue(isConnected)

        return networkLiveData
    }

        /**
         * Callback method when network is available
         */
        override fun onAvailable(network: Network) {
            networkLiveData.postValue(true)
        }

        /**
         * Callback method when network is lost
         */
        override fun onLost(network: Network) {
            networkLiveData.postValue(false)
    }






}
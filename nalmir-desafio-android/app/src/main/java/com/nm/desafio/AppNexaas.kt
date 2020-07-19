package com.nm.desafio

import androidx.multidex.MultiDexApplication
import com.nm.desafio.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class AppNexaas : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        iniDI()
    }

    private fun iniDI() {
        startKoin {
            androidLogger()
            androidContext(this@AppNexaas)
            modules(getAllModules())
        }
    }


}
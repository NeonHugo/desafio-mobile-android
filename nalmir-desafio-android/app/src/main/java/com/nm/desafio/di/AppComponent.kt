package com.nm.desafio.di

import com.nm.desafio.di.SystemModules.serviceModules
import com.nm.data.di.DataModule.mapperModule
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> = listOf(*getServiceModules())

    private fun getServiceModules(): Array<Module> {
        return arrayOf(
            serviceModules, mapperModule
        )
    }

}
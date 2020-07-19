package com.nm.desafio.di

import com.nm.data.repository.CartRepositoryImpl
import com.nm.data.repository.RemoteDataSource
import com.nm.data.repository.RemoteDataSourceImpl
import com.nm.data.services.NexaasService
import com.nm.desafio.ui.cart.CartViewModel
import com.nm.domain.repository.CartRepository
import com.nm.domain.usecase.CartUseCase
import com.nm.domain.usecase.CartUseCaseImpl
import com.nm.infra.net.data.RetrofitBuild.makeService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object SystemModules {
    const val apiService = "apiService"


    val serviceModules = module {
        single(named(apiService)) { makeService<NexaasService>("https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/") }

        viewModel { CartViewModel(get()) }

        single<RemoteDataSource> {
            RemoteDataSourceImpl(
                get(named(apiService)),
                get(named("pslCart"))
            )
        }

        single<CartRepository> { CartRepositoryImpl(get()) }

        single<CartUseCase> { CartUseCaseImpl(get()) }
    }
}
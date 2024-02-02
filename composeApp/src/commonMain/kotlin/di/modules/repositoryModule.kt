package di.modules

import data.repository.AuthRepositoryImpl
import data.repository.AuthRepository
import data.repository.ExchangeRepository
import data.repository.ExchangeRepositoryImpl
import data.repository.InventoryRepository
import data.repository.InventoryRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    factory <AuthRepository>{ AuthRepositoryImpl(get()) }
    factory <InventoryRepository>{ InventoryRepositoryImpl(get()) }
    factory <ExchangeRepository>{ ExchangeRepositoryImpl(get()) }
}
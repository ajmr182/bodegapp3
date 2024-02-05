package di.modules

import domain.GetExchangeUseCase
import domain.GetInventoryUseCase
import domain.SaveExchangeUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule : Module = module {
    factory { GetInventoryUseCase(get()) }
    factory { GetExchangeUseCase(get()) }
    factory { SaveExchangeUseCase(get()) }
}
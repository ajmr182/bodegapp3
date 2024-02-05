package di.modules

import org.koin.core.module.Module
import org.koin.dsl.module
import ui.features.exchange.ExchangeDataManager
import ui.features.inventory.InventoryScreenDataManager

val dataManagerModule: Module = module {
    single { InventoryScreenDataManager(get(), get()) }
    single { ExchangeDataManager(get(), get()) }
}
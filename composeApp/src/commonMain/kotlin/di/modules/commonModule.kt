package di.modules

import data.SupabaseSetup
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule: Module = module {
    single { SupabaseSetup() }
}
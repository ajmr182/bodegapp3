package di

import di.modules.commonModule
import di.modules.dataManagerModule
import di.modules.repositoryModule
import di.modules.useCaseModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(commonModule,dataManagerModule, repositoryModule, useCaseModule)
    }
}
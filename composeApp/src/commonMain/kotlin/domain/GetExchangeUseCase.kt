package domain

import data.models.ExchangeForTheDayModel
import data.repository.ExchangeRepository

class GetExchangeUseCase(private val exchangeRepository: ExchangeRepository)  {

    suspend fun getExchange(): ExchangeForTheDayModel {
        return exchangeRepository.getExchange()
    }
}
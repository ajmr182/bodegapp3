package domain

import data.models.ExchangeForTheDayModel
import data.repository.ExchangeRepository

class SaveExchangeUseCase (private val exchangeRepository: ExchangeRepository)  {
    suspend fun saveExchange(model: ExchangeForTheDayModel):Boolean {
        return exchangeRepository.saveExchange(model)
    }
}
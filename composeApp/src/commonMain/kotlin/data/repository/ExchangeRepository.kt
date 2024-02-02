package data.repository

import data.models.ExchangeForTheDayModel

interface ExchangeRepository {
    suspend fun saveExchange(model: ExchangeForTheDayModel): Boolean

    suspend fun getExchange(): ExchangeForTheDayModel
}
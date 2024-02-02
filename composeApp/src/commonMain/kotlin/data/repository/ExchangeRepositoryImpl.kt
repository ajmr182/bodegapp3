package data.repository

import data.SupabaseSetup
import data.models.ExchangeForTheDayModel
import io.github.jan.supabase.postgrest.postgrest

class ExchangeRepositoryImpl (private val supabaseClient: SupabaseSetup) :
    ExchangeRepository {
    override suspend fun saveExchange(model: ExchangeForTheDayModel): Boolean {
        return runCatching {
            supabaseClient.client.postgrest["ExchangeForTheDay"].update(
                {
                    set("date", model.date)
                    set("exchange",model.exchange)
                }
            ) {
                eq("id", 1)
            }
            true
        }.getOrElse  {
            false
        }
    }

    override suspend fun getExchange(): ExchangeForTheDayModel {
        return runCatching {
            supabaseClient.client.postgrest["ExchangeForTheDay"].select().decodeSingle<ExchangeForTheDayModel>()
        } .getOrElse {
            ExchangeForTheDayModel()
        }
    }
}
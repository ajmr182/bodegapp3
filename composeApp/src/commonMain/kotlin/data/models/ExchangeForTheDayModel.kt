package data.models

import kotlinx.serialization.Serializable

@Serializable
public data class ExchangeForTheDayModel(
    var id:Int=1,
    var date:String="",
    var exchange:String=""
)

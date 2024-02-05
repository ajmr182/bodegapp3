package ui.features.exchange

import data.models.ExchangeForTheDayModel
import domain.GetExchangeUseCase
import domain.SaveExchangeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import ui.baseclass.BaseDataManager

class ExchangeDataManager(
    private val getExchangeUseCase: GetExchangeUseCase,
    private val saveExchangeUseCase: SaveExchangeUseCase
) : BaseDataManager() {

    private val _changeOfTheDayModel = MutableStateFlow<ExchangeForTheDayModel>(ExchangeForTheDayModel())
    public val changeOfTheDayModel: StateFlow<ExchangeForTheDayModel> = _changeOfTheDayModel

    private val _showExchangeDialog = MutableStateFlow(false)
    public val showExchangeDialog: StateFlow<Boolean> = _showExchangeDialog

    private val _saveExchangeSuccess = MutableStateFlow(false)
    public val saveExchangeSuccess: StateFlow<Boolean> = _saveExchangeSuccess
    suspend fun getExchange() {
        showLoading(true)
        val success = withContext(Dispatchers.IO) {
            getExchangeUseCase.getExchange()
        }
        _changeOfTheDayModel.value = success
        showLoading(false)
    }

    suspend fun saveExchange(value: String) {
        showLoading(true)
        val model = ExchangeForTheDayModel(date = "03/12/2024", exchange = value )
        val success = withContext(Dispatchers.IO) {
            saveExchangeUseCase.saveExchange(model)
        }
        _saveExchangeSuccess.value = success
        showLoading(false)
    }
}
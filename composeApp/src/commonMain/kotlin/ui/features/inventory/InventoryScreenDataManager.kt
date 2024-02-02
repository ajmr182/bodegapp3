package ui.features.inventory

import data.models.ExchangeForTheDayModel
import data.models.InventoryModel
import data.models.Product
import domain.GetExchangeUseCase
import domain.GetInventoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import ui.baseclass.BaseDataManager

class InventoryScreenDataManager(
    private val getInventoryUseCase: GetInventoryUseCase,
    private val getExchangeUseCase: GetExchangeUseCase
) : BaseDataManager() {

    private val _inventory = MutableStateFlow(InventoryModel())
    val inventory: StateFlow<InventoryModel> = _inventory

    private val _originalListProducts = MutableStateFlow<List<Product>>(listOf())
    private val originalListProducts: StateFlow<List<Product>> = _originalListProducts

    private val _inventoryInfoSuccess = MutableStateFlow(false)
    val inventoryInfoSuccess: StateFlow<Boolean> = _inventoryInfoSuccess

    private val _changeOfTheDayModel = MutableStateFlow(ExchangeForTheDayModel())
    private val changeOfTheDayModel: StateFlow<ExchangeForTheDayModel> = _changeOfTheDayModel

    suspend fun getExchange() {
        showLoading(true)
        val success = withContext(Dispatchers.IO) {
            getExchangeUseCase.getExchange()
        }
        _changeOfTheDayModel.value = success
        showLoading(false)
        getInventory()
    }

    private suspend fun getInventory() {
        showLoading(true)
        val success = withContext(Dispatchers.IO) {
            getInventoryUseCase.getInventory()
        }
        _inventory.value = success
        _originalListProducts.value = success.listOfProducts
        _originalListProducts.value.forEach {
            it.productPriceBs =
                (it.productPrice.toDouble() * changeOfTheDayModel.value.exchange.toDouble()).toString()
        }
        _inventoryInfoSuccess.value = true
        showLoading(false)
    }
}
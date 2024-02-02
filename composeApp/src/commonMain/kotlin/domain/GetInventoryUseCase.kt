package domain

import data.models.InventoryModel
import data.repository.InventoryRepository

class GetInventoryUseCase (private val inventoryRepository: InventoryRepository)  {
    suspend fun getInventory(): InventoryModel {
        return inventoryRepository.getInventory()
    }
}
package data.repository

import data.models.InventoryModel

interface InventoryRepository {
    public suspend fun getInventory(): InventoryModel
    /*public suspend fun insertNewDataToInventory(product: Product): Boolean
    public suspend fun updateNewDataToInventory(product:Product): Boolean
    public suspend fun deleteDataInventory(product:Product): Boolean*/
}
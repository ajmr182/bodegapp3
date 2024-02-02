package data.repository

import data.SupabaseSetup
import data.models.InventoryModel
import data.models.Product
import io.github.jan.supabase.postgrest.postgrest

public class InventoryRepositoryImpl(private val supabaseClient: SupabaseSetup) :
    InventoryRepository {
    override suspend fun getInventory(): InventoryModel {
        return runCatching {
            InventoryModel(listOfProducts = supabaseClient.client.postgrest["Inventory"].select().decodeList<Product>())
        } .getOrElse  {
            InventoryModel(listOf())
        }
    }

   /* override suspend fun insertNewDataToInventory(product:Product): Boolean {
        runCatching {
            supabaseClient.client.postgrest["Inventory"].insert(product)
            return true
        } .getOrElse  {
            return false
        }
    }

    override suspend fun updateNewDataToInventory(product:Product): Boolean {
        return runCatching {
            supabaseClient.client.postgrest["Inventory"].update(
                {
                    set("productName", product.productName)
                    set("productPrice",product.productPrice)
                    set("productQuantity",product.productQuantity)
                }
            ) {
                eq("id", product.productId)
            }
            true
        }.getOrElse  {
            false
        }
    }

    override suspend fun deleteDataInventory(product:Product): Boolean {
        return runCatching {
            supabaseClient.client.postgrest["Inventory"].delete{
                eq("id", product.productId)
            }
            true
        } .getOrElse  {
            false
        }
    }*/
}


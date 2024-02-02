package data.models

import kotlinx.serialization.Serializable

@Serializable
data class InventoryModel (
    var listOfProducts: List<Product> = listOf()
)

@Serializable
data class Product(
    var productId:Int,
    var productName:String,
    var productPrice:String,
    var productQuantity:String,
    var productPriceBs:String=""
)
package ui.features.inventory

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.models.Product
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import ui.baseclass.BaseScreen
import com.ajmr182.bodegaap3.SharedRes

class InventoryScreenClass: BaseScreen<InventoryScreenDataManager>(InventoryScreenDataManager::class) {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun InventoryScreen() {
        LoadingIndicator()
        val inventoryScreenDataManager: InventoryScreenDataManager by KoinJavaComponent.inject(
            InventoryScreenDataManager::class.java
        )

        LaunchedEffect(inventoryScreenDataManager.inventory.value) {
            inventoryScreenDataManager.getExchange()
        }
        val scrollState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        if (inventoryScreenDataManager.inventoryInfoSuccess.collectAsState().value) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .draggable(
                        orientation = Orientation.Vertical,
                        state = rememberDraggableState { delta ->
                            coroutineScope.launch {
                                scrollState.scrollBy(-delta)
                            }
                        },
                    ).background(Color.LightGray)
            ) {
                stickyHeader {
                    GridLayoutHeader()
                }

                items(inventoryScreenDataManager.inventory.value.listOfProducts) { product ->
                    GridLayoutItem(product)
                    Divider()
                }
            }
        }
    }

    @Composable
    fun GridLayoutHeader() {
        Row(
            modifier = Modifier
                .fillMaxWidth().background(MaterialTheme.colors.primary).padding(vertical = 8.dp)
                .padding(start = 4.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                StickyHeaderItem(stringResource(SharedRes.strings.string_header_name))
            }
            Column(modifier = Modifier.weight(1f)) {
                StickyHeaderItem(stringResource(SharedRes.strings.string_header_quantity))
            }
            Column(modifier = Modifier.weight(1f)) {
                StickyHeaderItem(stringResource(SharedRes.strings.string_header_price_usd))
            }
            Column(modifier = Modifier.weight(1f)) {
                StickyHeaderItem(stringResource(SharedRes.strings.string_header_price_bs))
            }
        }
    }

    @Composable
    fun StickyHeaderItem(title: String) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            color = Color.White
        )
    }

    @Composable
    fun GridLayoutItem(productTest: Product) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 4.dp).padding(start = 4.dp),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = productTest.productName,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = productTest.productQuantity.toString(),
                    overflow = TextOverflow.Clip,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = productTest.productPrice,
                    overflow = TextOverflow.Clip,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = productTest.productPriceBs,
                    overflow = TextOverflow.Clip,
                )
            }
        }
    }
}
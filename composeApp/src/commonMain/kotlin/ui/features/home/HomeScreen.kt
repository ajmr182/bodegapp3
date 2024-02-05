package ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import ui.baseclass.navigationItems
import ui.features.exchange.ExchangeScreenClass
import ui.features.inventory.InventoryScreenClass

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        var selectedItem by remember { mutableIntStateOf(0) }
        Column(Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                SelectScreen(selectedItem)
            }
            BottomAppBar {
                navigationItems.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = "") },
                        label = { Text(item.title.invoke()) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectScreen(selectedItem: Int) {
    when (selectedItem) {
        0 -> {
            InventoryScreenClass().InventoryScreen()
        }
        1->{

        }
        2->{
            ExchangeScreenClass().ExchangeScreen()
        }
    }
}
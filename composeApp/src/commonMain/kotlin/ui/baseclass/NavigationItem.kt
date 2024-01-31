package ui.baseclass

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajmr182.bodegaap3.SharedRes
import dev.icerock.moko.resources.compose.stringResource

data class NavigationItem(
    var title: @Composable () -> String,
    var icon: ImageVector
)

val navigationItems = listOf(
    NavigationItem({ stringResource(SharedRes.strings.string_menu_inventory) }, Icons.Filled.Favorite),
    NavigationItem({ stringResource(SharedRes.strings.string_menu_inventory) }, Icons.Filled.Person),
    NavigationItem({ stringResource(SharedRes.strings.string_menu_inventory) }, Icons.Filled.List)
)

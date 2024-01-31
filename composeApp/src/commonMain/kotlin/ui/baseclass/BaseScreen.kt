package ui.baseclass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject
import kotlin.reflect.KClass

public open class BaseScreen<T : BaseDataManager>(private val dataManagerClass: KClass<T>) : KoinComponent {

    private val dataManager: T by inject(dataManagerClass.java)

    @Composable
    public fun LoadingIndicator() {
        val loadingState by dataManager.loading.collectAsState()
            if (loadingState) {
                ShowLoading()
            }
    }

    @Composable
    private fun ShowLoading() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
package ui.features.exchange

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ajmr182.bodegaap3.SharedRes
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import ui.baseclass.BaseScreen

class ExchangeScreenClass : BaseScreen<ExchangeDataManager>(ExchangeDataManager::class) {

    private val exchangeDataManager: ExchangeDataManager by KoinJavaComponent.inject(
        ExchangeDataManager::class.java
    )

    @Composable
    fun ExchangeScreen() {
        LaunchedEffect(Unit) {
            exchangeDataManager.getExchange()
        }

        LaunchedEffect(exchangeDataManager.saveExchangeSuccess.value) {
            exchangeDataManager.getExchange()
        }

        Scaffold {
            val text = remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
            ) {
                if (exchangeDataManager.changeOfTheDayModel.collectAsState().value.exchange.isNotEmpty()) {
                    Text(
                        text = exchangeDataManager.changeOfTheDayModel.value.exchange,
                        modifier = Modifier.fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            exchangeDataManager.saveExchange(text.value)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(stringResource(SharedRes.strings.string_confirm))
                }
            }
        }
    }
}
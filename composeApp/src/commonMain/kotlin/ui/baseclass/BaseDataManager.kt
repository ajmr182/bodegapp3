package ui.baseclass

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

public open class BaseDataManager {

    private var _loading = MutableStateFlow<Boolean>(false)
    public val loading: StateFlow<Boolean> = _loading

    public fun showLoading(value:Boolean){
        _loading.value = value
    }
}
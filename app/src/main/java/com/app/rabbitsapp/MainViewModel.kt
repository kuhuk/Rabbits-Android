package com.app.rabbitsapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.rabbitsapp.data.Rabbit
import com.app.rabbitsapp.data.RabbitsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: RabbitsAPI
): ViewModel() {

    private val _state = mutableStateOf(RabbitState())
    val state: State<RabbitState> = _state

    init {
        getRandomRabbit()
    }

    fun getRandomRabbit() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                _state.value = _state.value.copy(
                    rabbit = api.getRandomRabbit(),
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("MainViewModel", e.message.toString())
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    data class RabbitState(
        val rabbit: Rabbit? = null,
        val isLoading: Boolean = false
    )
}
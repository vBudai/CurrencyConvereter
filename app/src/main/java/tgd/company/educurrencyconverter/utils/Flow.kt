package tgd.company.educurrencyconverter.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

fun <T> Flow<T>.setUpWorkDispatchers() = this.flowOn(Dispatchers.IO)
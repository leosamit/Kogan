package com.samit.kogantest.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.samit.kogantest.data.repo.ProductRepository
import com.samit.kogantest.di.qualifier.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    val products by lazy {
        repository.observeRemotePagedSets(
            ioCoroutineScope
        )
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}
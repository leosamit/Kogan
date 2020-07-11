package com.samit.kogantest.di.builder

import androidx.lifecycle.ViewModel
import com.samit.kogantest.di.qualifier.ViewModelKey
import com.samit.kogantest.ui.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViewModel(viewModel: ProductViewModel): ViewModel
}
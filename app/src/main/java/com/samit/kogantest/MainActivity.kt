package com.samit.kogantest

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samit.kogantest.databinding.ActivityMainBinding
import com.samit.kogantest.di.injectViewModel
import com.samit.kogantest.ui.ProductViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProductViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = injectViewModel(viewModelFactory)
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.products.observe(this@MainActivity, Observer {
            binding.products = it
        })
    }
}
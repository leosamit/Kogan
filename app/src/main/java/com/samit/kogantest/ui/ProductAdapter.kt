package com.samit.kogantest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samit.kogantest.R
import com.samit.kogantest.databinding.ItemProductBinding
import com.samit.kogantest.ui.model.ProductUI

class ProductAdapter : PagedListAdapter<ProductUI, ProductViewHolder>(ProductDiffCallback()) {

    private class ProductDiffCallback : DiffUtil.ItemCallback<ProductUI>() {
        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.title == oldItem.title
        }

        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemProductBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_product, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val followable = getItem(position)
        holder.bindData(followable)
    }
}

class ProductViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(product: ProductUI?) {
        binding.product = product
    }
}
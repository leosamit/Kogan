package com.samit.kogantest.binding

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.samit.kogantest.ui.ProductAdapter
import com.samit.kogantest.ui.model.ProductUI

@BindingAdapter(value = ["app:pageProductList"])
fun setProductPageListItems(rv: RecyclerView, productList: PagedList<ProductUI>?) {
    if (rv.adapter as? ProductAdapter == null) {
        rv.adapter = ProductAdapter()
    }
    (rv.adapter as? ProductAdapter)?.submitList(productList)
    rv.adapter?.notifyDataSetChanged()
}
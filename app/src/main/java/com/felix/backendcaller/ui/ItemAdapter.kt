package com.felix.backendcaller.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felix.backendcaller.databinding.ProductItemBinding
import com.felix.backendcaller.models.Item

class ItemAdapter(var items: List<Item>): RecyclerView.Adapter<ItemAdapter.ItemsViewHolder>() {
    class ItemsViewHolder(private val binding: ProductItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(items: Item){
            binding.apply {
                productID.text = items.id.toString()
                productName.text = items.name
                productPrice.text = items.price.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
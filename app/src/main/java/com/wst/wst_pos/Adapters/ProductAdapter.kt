package com.wst.wst_pos.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wst.wst_pos.R

class ProductAdapter {

    class ProductAdapter(private val context: Context, private val products: List<Product>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val product = products[position]
            holder.imageView.setImageResource(product.imageRes)
            holder.nameView.text = product.name
            holder.priceView.text = context.getString(R.string.price_format, product.price)
        }

        override fun getItemCount() = products.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
            val nameView: TextView = itemView.findViewById(R.id.nameView)
            val priceView: TextView = itemView.findViewById(R.id.priceView)
        }
    }


}
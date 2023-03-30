package com.wst.wst_pos.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wst.wst_pos.MainActivity
import com.wst.wst_pos.R
import com.wst.wst_pos.sellPage.SellFragment

class CategoryAdapter(private val context: Context, private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedItemPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.textView.text = category

        holder.itemView.isSelected = position == selectedItemPosition
        holder.itemView.setOnClickListener {
            // Update the selected item position and notify the adapter
            val oldPosition = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedItemPosition)

            // Pass the selected category to the ProductFragment
            (context as MainActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SellFragment.newInstance(category))
                .commit()
        }
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_category)
    }
}

package com.wst.wst_pos.sellPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wst.wst_pos.Adapters.CategoryAdapter
import com.wst.wst_pos.R
import com.wst.wst_pos.databinding.FragmentSellBinding


class SellFragment : Fragment() {

    private val viewModel : SellViewModel by lazy {
        ViewModelProvider(this).get(SellViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentSellBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.categoryRecyclerView.adapter = SellPageAdapterCategory()

    }

}
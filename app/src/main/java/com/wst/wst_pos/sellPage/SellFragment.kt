package com.wst.wst_pos.sellPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wst.wst_pos.Adapters.CategoryAdapter
import com.wst.wst_pos.R



class SellFragment : Fragment() {

    private var selectedCategory: String? = null

    // Declare the RecyclerView and adapters as properties of the Fragment
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productRecyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the selected category from the arguments
        selectedCategory = arguments?.getString(ARG_CATEGORY)

        // Initialize the category RecyclerView and adapter
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)
        categoryAdapter = CategoryAdapter(requireContext(), categories)
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Initialize the product RecyclerView and adapter
        productRecyclerView = view.findViewById(R.id.productRecyclerView)
        productAdapter = ProductAdapter(requireContext())
        productRecyclerView.adapter = productAdapter
        productRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    companion object {
        private const val ARG_CATEGORY = "category"

        // Add a newInstance method that accepts the selected category
        fun newInstance(category: String?): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }

        // Define the available categories as a static list
        private val categories = listOf("Category 1", "Category 2", "Category 3", "Category 4")
    }
}
package com.wst.wst_pos.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wst.wst_pos.R

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_items, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.barcode -> {
                // Handle settings action
                Toast.makeText(requireContext(), "SucCses", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.cart -> {
                // Handle help action
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
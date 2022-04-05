package com.example.aaahome2.ui.wishlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.R
import com.example.aaahome2.Tools
import com.example.aaahome2.databinding.FragmentWishlistBinding
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.model.WishMap
import com.example.aaahome2.network.service.Network
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import kotlin.collections.ArrayList

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    lateinit var recycler: RecyclerView
    lateinit var viewModel: WishViewModel
    lateinit var adapter:WishItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel=ViewModelProvider(this).get(WishViewModel::class.java)
        val layoutManager = LinearLayoutManager(AAAhomeApplication.context)
        recycler= binding.recyclerWish
        viewModel.initList()
        refreshView()
        recycler.layoutManager = layoutManager
        binding.swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.initList()
            refreshView()
            binding.swipeRefresh.isRefreshing=false
        }
        return root
    }
    private fun refreshView(){
        viewModel.listData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter = WishItemAdapter(AAAhomeApplication.context, viewModel.listData.value!!)
            recycler.adapter = adapter
        })
    }

    override fun onStop() {
        for(i in viewModel.listData.value!!){
            Tools.changeGame(i.id,i.nearlyLowestTimestamp)
        }
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
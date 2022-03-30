package com.example.aaahome2.ui.discount

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.databinding.FragmentDiscountBinding
import com.example.aaahome2.model.WishItem
import com.example.aaahome2.network.service.Network
import com.example.aaahome2.ui.library.LibraryItemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DiscountFragment : Fragment() {

    private var _binding: FragmentDiscountBinding? = null
    private val wishList = ArrayList<WishItem>()
    private val binding get() = _binding!!
    lateinit var recycler: RecyclerView
    var index = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AAAhomeApplication.mes="长按封面加入愿望单！"
        _binding = FragmentDiscountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recycler = binding.recyclerDiscount
        recycler.addOnScrollListener(NewScrollListener())
        initList()
        return root
    }


    inner class NewScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            val lm = recyclerView.layoutManager as LinearLayoutManager?
            val totalItemCount = recyclerView.adapter!!.itemCount
            val lastVisibleItemPosition = lm!!.findLastVisibleItemPosition()
            val visibleItemCount = recyclerView.childCount

            if (newState === RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalItemCount - 1 && visibleItemCount > 0) {
                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        for (i in 1..12)
                            wishList.add(Network.getGameById(index++))
                        Log.d("mes", "onScrollStateChanged: $index")
                        withContext(Dispatchers.Main) {
                            recyclerView.adapter?.notifyItemChanged(index)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initList() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                for (i in 1..12)
                    wishList.add(Network.getGameById(index++))
            }
            withContext(Dispatchers.Main) {
                val layoutManager = GridLayoutManager(AAAhomeApplication.context, 2)
                recycler.layoutManager = layoutManager
                val adapter = LibraryItemAdapter(AAAhomeApplication.context, wishList)
                recycler.adapter = adapter
            }
        }
    }
}
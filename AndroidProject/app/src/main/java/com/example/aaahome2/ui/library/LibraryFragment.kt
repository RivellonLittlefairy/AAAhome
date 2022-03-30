package com.example.aaahome2.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.databinding.FragmentLibraryBinding
import com.example.aaahome2.model.WishItem

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val wishList=ArrayList<WishItem>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AAAhomeApplication.mes="长按封面加入愿望单！"
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recycler: RecyclerView = binding.recyclerview
        initList()
        val layoutManager= GridLayoutManager(AAAhomeApplication.context,2)
        recycler.layoutManager=layoutManager
        val adapter=LibraryItemAdapter(AAAhomeApplication.context,wishList)
        recycler.adapter=adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initList() {
    }
}
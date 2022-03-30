package com.example.aaahome2.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aaahome2.AAAhomeApplication
import com.example.aaahome2.Tools.Companion.showMes
import com.example.aaahome2.databinding.FragmentMeBinding
import com.example.aaahome2.ui.login.LoginActivity


class MeFragment : Fragment() {
    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AAAhomeApplication.mes=""
        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val edit =
            AAAhomeApplication.context.getSharedPreferences("token", 0)
        val email = edit.getString("email", "")
        binding.emailtext.text=email
        binding.exit.setOnClickListener {
            val edit =
                AAAhomeApplication.context.getSharedPreferences("token", 0).edit()
            edit.putString("token", "")
            edit.apply()
            AAAhomeApplication.startActivity<LoginActivity>()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
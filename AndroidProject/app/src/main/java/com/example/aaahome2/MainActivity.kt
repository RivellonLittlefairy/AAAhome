package com.example.aaahome2


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aaahome2.Tools.Companion.showMes
import com.example.aaahome2.databinding.ActivityMainBinding
import com.example.aaahome2.ui.detail.DetailActivity
import com.google.android.material.internal.ContextUtils.getActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        super.onCreate(savedInstanceState)
        //强行设置全屏，不好用
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_discount,
                R.id.navigation_wishlist,
                R.id.navigation_me
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val button = findViewById<Button>(R.id.search)
        button.setOnClickListener {
            val toast =
                Toast.makeText(AAAhomeApplication.context, "search!!!", Toast.LENGTH_SHORT)
            toast.show()
        }
        findViewById<Button>(R.id.help).setOnClickListener {
            showMes(AAAhomeApplication.mes)
        }

    }
}

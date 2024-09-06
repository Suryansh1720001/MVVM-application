package com.itssuryansh.mvvmapplication.Screen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.itssuryansh.mvvmapplication.R
import com.itssuryansh.mvvmapplication.databinding.ActivityMainBinding
import com.itssuryansh.mvvmapplication.sealedClass.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val NewsViewModel: NewsViewModel by viewModels()
    private lateinit var NewsAdapter : MainAcitivityAdapter
    private lateinit var binding: ActivityMainBinding

//    lateinit var NewsViewModel : NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val repository = (application as NewsApplication).newsRepository
//        NewsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        NewsViewModel.news.observe(this){ articles ->

            when(articles){

               is  Response.Success ->{
                   NewsAdapter = MainAcitivityAdapter(articles.data!!)
                   binding?.rvList?.apply {
                       layoutManager = LinearLayoutManager(this@MainActivity)
                       adapter = NewsAdapter
                   }


               }
                is Response.Loading ->{

                    Toast.makeText(this,"Loading",Toast.LENGTH_LONG).show()

                }
                is Response.Failure -> {
                    Toast.makeText(this,"${articles.failureMessage}",Toast.LENGTH_LONG).show()

                }

                is Response.Error -> {
                    Toast.makeText(this, "${articles.errorMessage}", Toast.LENGTH_LONG).show()

                }

            }





        }


    }
}
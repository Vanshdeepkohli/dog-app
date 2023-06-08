package com.example.dogappwithrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout.DispatchChangeEvent
import com.example.dogappwithrecyclerview.Adapter.ViewPagerAdapter
import com.example.dogappwithrecyclerview.databinding.ActivityPhotoBinding
import com.example.dogappwithrecyclerview.network.ApiClientRetrofit
import com.example.dogappwithrecyclerview.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoActivity : AppCompatActivity(), ViewPagerAdapter.AdapterViewInterface {
    private lateinit var binding : ActivityPhotoBinding
    private lateinit var URL : String
    var listOfImages : List<String>  = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val retrofit = ApiClientRetrofit.retrofit.create(ApiInterface::class.java)


        CoroutineScope(Dispatchers.IO).launch{
            val result = retrofit.getByBreeds(name!!)
            listOfImages = result.body()!!.message

            withContext(Dispatchers.Main){
                binding.viewPager.adapter = ViewPagerAdapter(this@PhotoActivity,listOfImages,this@PhotoActivity)
            }
        }


    }

    override fun onItemClick(position: Int) {
        URL = listOfImages[position]
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, URL)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
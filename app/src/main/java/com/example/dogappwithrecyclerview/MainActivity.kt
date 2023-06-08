package com.example.dogappwithrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout.DispatchChangeEvent
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogappwithrecyclerview.Adapter.DogsBreedAdapter
import com.example.dogappwithrecyclerview.databinding.ActivityMainBinding
import com.example.dogappwithrecyclerview.network.AllBreedsList
import com.example.dogappwithrecyclerview.network.ApiClientRetrofit
import com.example.dogappwithrecyclerview.network.ApiInterface
import com.example.dogappwithrecyclerview.network.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), DogsBreedAdapter.RecyclerViewEvent {
    private lateinit var binding : ActivityMainBinding
    private val list  = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // base url search all breeds https://dog.ceo/api/breeds/list/all
        // base url search by breed https://dog.ceo/api/breed/hound/images

        val retrofit = ApiClientRetrofit.retrofit.create(ApiInterface::class.java)


        list.add("hound")
        for(i in 2..20){
            list.add(i.toString())
        }

        binding.rvList.adapter = DogsBreedAdapter(list, this)
        binding.rvList.layoutManager = GridLayoutManager(this,2)


    }

    override fun onItemClick(position: Int) {
        val breedOfDogToSearch = list[position]

        val intent = Intent(this,PhotoActivity::class.java)
        intent.putExtra("name", breedOfDogToSearch)
        startActivity(intent)

    }

}
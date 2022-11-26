package com.example.zoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.plcoding.retrofitcrashcourse.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var animal: Animal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        refresh()
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                Instance.api.getAnimals()
            } catch(e: IOException) {
                Log.e(TAG, "ERROR INTERNET")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "ERROR API")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                animal.animals = response.body()!!
            } else {
                Log.e(TAG, "ERROR SERVER")
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun refresh() {
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(this, "Sucsessfully updated", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView() = binding.rvAnimals.apply {
        animal = Animal()
        adapter = animal
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}
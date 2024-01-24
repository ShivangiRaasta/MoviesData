package com.example.moviesdata.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesdata.adapter.UploadListAdapter
import com.example.moviesdata.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagingAdapter: UploadListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pagingAdapter = UploadListAdapter(UploadListAdapter.UserComparator)
        val state = viewModel.state.value
        initializeRecyclerView()

        if (state.error.isNotBlank()) {
            Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
        }

        viewModel.readPopularData.observe(this@MainActivity) {
            GlobalScope.launch {
                binding.progressBar.visibility = View.GONE
                pagingAdapter.submitData(it)
            }
        }

        viewModel.readRatedData.observe(this@MainActivity) {
            GlobalScope.launch {
                binding.progressBar.visibility = View.GONE
                pagingAdapter.submitData(it)
            }
        }

        binding.ivMore.setOnClickListener {
            openDialog(binding.ivMore)
        }
    }

    private fun initializeRecyclerView() {
        binding.rlList.layoutManager = GridLayoutManager(this, 2)
        binding.rlList.adapter = pagingAdapter
    }

    private fun openDialog(view: View) {
        val menu = PopupMenu(this, view)
        menu.menu.add("Sort by Popular")
        menu.menu.add("Sort by Rating")

        menu.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.title) {
                "Sort by Popular" -> {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.getPopularMovie()
                }

                "Sort by Rating" -> {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.getRatedMovie()
                }
            }
            true
        }

        menu.show()
    }
}
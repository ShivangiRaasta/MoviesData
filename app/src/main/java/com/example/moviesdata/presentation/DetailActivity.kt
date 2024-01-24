package com.example.moviesdata.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesdata.R
import com.example.moviesdata.databinding.ActivityDetailBinding
import com.example.moviesdata.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        id = intent.getStringExtra("id").toString()
        viewModel.getMovieDetail(id)

        viewModel.readData.observe(this@DetailActivity) {
            binding.progressBar.visibility = View.GONE
            Glide.with(this@DetailActivity)
                .load(Constants.IMAGE_BASE_URL + it.backdrop_path)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.ivImage)
            binding.tvTitle.text = it.title
            binding.tvDesc.text = it.overview
            var genres = ""
            for (items in it.genres) {
                genres = genres + items.name + " " + "\u00b7" + " "
            }
            binding.tvGenres.text = genres.removeSuffix("\u00b7 ")

            val hours: Int = it.runtime / 60
            val minutes: Int = it.runtime % 60
            binding.tvTime.text = hours.toString() + "hr" + minutes + "mins"
        }
    }
}
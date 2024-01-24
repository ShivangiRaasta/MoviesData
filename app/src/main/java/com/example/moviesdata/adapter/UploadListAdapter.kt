package com.example.moviesdata.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesdata.R
import com.example.moviesdata.model.Movie
import com.example.moviesdata.presentation.DetailActivity
import com.example.moviesdata.utils.Constants

 class UploadListAdapter(diffCallback: DiffUtil.ItemCallback<Movie>) :
        PagingDataAdapter<Movie, UploadListAdapter.ViewHolder>(diffCallback) {
    lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View =
            layoutInflater.inflate(R.layout.row_movie, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

      //  holder.tvTitle.text = myListData.original_title
        Glide.with(context)
            .load(Constants.IMAGE_BASE_URL+item?.poster_path)
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.ivImage)

        holder.cardView.setOnClickListener {
            context.startActivity(Intent(context,DetailActivity::class.java)
                .putExtra("id",item?.id.toString()))
        }
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var ivImage: ImageView
        var cardView: CardView

        init {
            tvTitle =
                itemView.findViewById<View>(R.id.tvTitle) as TextView
            ivImage =
                itemView.findViewById<View>(R.id.ivImage) as ImageView
            cardView =
                itemView.findViewById<View>(R.id.cardView) as CardView
        }
    }

     object UserComparator : DiffUtil.ItemCallback<Movie>() {
         override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
             // Id is unique.
             return oldItem.id == newItem.id
         }

         override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
             return oldItem == newItem
         }
     }
}

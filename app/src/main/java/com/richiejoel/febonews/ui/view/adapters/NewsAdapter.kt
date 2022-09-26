package com.richiejoel.febonews.ui.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.richiejoel.febonews.data.model.Articles
import com.richiejoel.febonews.databinding.NewsBinding
import com.richiejoel.febonews.utils.UtilsView

class NewsAdapter(private var newsModel: ArrayList<Articles>, private val context: Context, var listener: OnItemClickListener):
    RecyclerView.Adapter<NewsAdapter.NewsHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            NewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news: Articles = newsModel[position]
        holder.bind(news= news, context= context, listener= listener)

    }

    override fun getItemCount(): Int {
        return newsModel.size
    }

    fun setData(data: List<Articles>){
        newsModel.addAll(data)
        notifyDataSetChanged()
    }

    class NewsHolder(var binding: NewsBinding):  RecyclerView.ViewHolder(binding.root){

        lateinit var newsSelected: Articles

        fun bind(news: Articles, context: Context, listener: OnItemClickListener){
            newsSelected = news
            binding.titleNews.text = news.title
            binding.dateNews.text = news?.publishedAt?.let { UtilsView.getFormatDateFromDate(it) }
            binding.sourceNews.text = "Source: ${news.source?.name}"
            Glide.with(context).load(news.urlToImage).into(binding.imgNews)

            binding.cardViewNews.setOnClickListener {
                listener.onItemClick(news, adapterPosition)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(news: Articles, position: Int)
    }

}
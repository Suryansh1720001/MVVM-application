package com.itssuryansh.mvvmapplication.Screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itssuryansh.mvvmapplication.R
import com.itssuryansh.mvvmapplication.entities.Article
import com.itssuryansh.mvvmapplication.entities.News

class MainAcitivityAdapter(val News: News) :
    RecyclerView.Adapter<MainAcitivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return News.articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = News.articles[position]
        holder.bind(news)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id = itemView.findViewById<TextView>(R.id.id)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.descrption)
        private val author: TextView = itemView.findViewById(R.id.author)


        fun bind(article: Article) {
            id.text = article.id.toString()
            title.text = article.title
            description.text = article.description
            author.text = article.author

        }

    }
}



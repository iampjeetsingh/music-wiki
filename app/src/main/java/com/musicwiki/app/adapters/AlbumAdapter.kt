package com.musicwiki.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.musicwiki.app.R
import com.musicwiki.app.models.Album

class AlbumAdapter(
    private val list: MutableList<Album>
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.album_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.load(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(
        view: View,
        private val image: ImageView = view.findViewById(R.id.image),
        private val title: TextView = view.findViewById(R.id.title),
        private val artist: TextView = view.findViewById(R.id.artist)
    ) : RecyclerView.ViewHolder(view) {
        fun load(album : Album){
            val i = album.image.size
            Glide.with(image).load(album.image[i-1].url).into(image)
            title.text = album.name
            artist.text = album.artist.name
        }
    }
}
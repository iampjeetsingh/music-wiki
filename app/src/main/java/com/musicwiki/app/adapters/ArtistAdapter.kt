package com.musicwiki.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.musicwiki.app.R
import com.musicwiki.app.models.Artist

class ArtistAdapter(
    private val list: MutableList<Artist>
) : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.artist_layout, parent, false)
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
        private val name: TextView = view.findViewById(R.id.name)
    ) : RecyclerView.ViewHolder(view) {
        fun load(artist : Artist){
            val i = artist.image.size
            Glide.with(image).load(artist.image[i-1].url).into(image)
            name.text = artist.name
        }
    }
}
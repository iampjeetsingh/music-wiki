package com.musicwiki.app.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.musicwiki.app.R
import com.musicwiki.app.adapters.AlbumAdapter
import com.musicwiki.app.adapters.ArtistAdapter
import com.musicwiki.app.adapters.TrackAdapter
import com.musicwiki.app.api.RetrofitHelper
import com.musicwiki.app.api.TagService
import com.musicwiki.app.models.Album
import com.musicwiki.app.models.Artist
import com.musicwiki.app.models.Tag
import com.musicwiki.app.models.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TagInfoActivity : AppCompatActivity() {
    private val TAG = "TagInfoActivity"
    lateinit var tag: Tag
    lateinit var tagTitle: TextView
    lateinit var tagDescription: TextView

    var albums: MutableList<Album> = mutableListOf()
    val albumAdapter: AlbumAdapter = AlbumAdapter(albums)

    var artists: MutableList<Artist> = mutableListOf()
    var artistAdapter: ArtistAdapter = ArtistAdapter(artists)

    var tracks: MutableList<Track> = mutableListOf()
    var trackAdapter: TrackAdapter = TrackAdapter(tracks)

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_info)

        tagTitle = findViewById(R.id.tagTitle)
        tagDescription = findViewById(R.id.tagDescription)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = albumAdapter

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.text!!.equals("ALBUMS")){
                    recyclerView.adapter = albumAdapter
                }else if(tab!!.text!!.equals("ARTISTS")){
                    recyclerView.adapter = artistAdapter
                }else if(tab!!.text!!.equals("TRACKS")){
                    recyclerView.adapter = trackAdapter
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        val tagName:String = intent.getStringExtra("tag")!!

        val tagService: TagService = RetrofitHelper.getInstance().create(TagService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            tag = tagService.getTagInfo(tagName).tag
            withContext(Dispatchers.Main){
                tagTitle.text = tag.name
                tagDescription.text = Html.fromHtml(tag.wiki.summary)
            }
            withContext(Dispatchers.Main){
                albums.addAll(tagService.getTopAlbums(tagName).albums.album)
                artists.addAll(tagService.getTagArtists(tagName).topartists.artist)
                tracks.addAll(tagService.getTagTracks(tagName).tracks.track)
                Log.e("TAGINFO", "albums: "+albums.size+" artists: "+artists.size+" tracks: "+tracks.size)
                albumAdapter.notifyDataSetChanged()
            }
        }
    }

}
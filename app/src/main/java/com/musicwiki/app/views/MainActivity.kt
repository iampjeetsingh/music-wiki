package com.musicwiki.app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.musicwiki.app.BuildConfig
import com.musicwiki.app.R
import com.musicwiki.app.api.RetrofitHelper
import com.musicwiki.app.api.TagService
import com.musicwiki.app.models.Tag
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var extrasVisible: Boolean = false

    private lateinit var chipGroup: ChipGroup
    private lateinit var toggleIcon: ImageView
    private lateinit var tagList: List<Tag>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipGroup = findViewById(R.id.chipGroup)
        toggleIcon = findViewById(R.id.toggleIcon)

        val tagService: TagService = RetrofitHelper.getInstance().create(TagService::class.java)

        CoroutineScope(Dispatchers.IO).launch{
            tagList = tagService.getTopTags().toptags.tag
            withContext(Dispatchers.Main) {
                refresh()
            }
        }
    }

    private fun refresh(){
        if (extrasVisible){
            toggleIcon.setImageResource(R.drawable.ic_expand_less)
        }else{
            toggleIcon.setImageResource(R.drawable.ic_expand_more)
        }
        chipGroup.removeAllViews()
        for(i in 1 until tagList.size){
            if(i==10 && !extrasVisible){
                break
            }
            val tag = tagList[i]
            val chip = Chip(this)
            chip.text = tag.name
            chip.setOnClickListener {
                val intent = Intent(this, TagInfoActivity::class.java)
                intent.putExtra("tag", tag.name)
                startActivity(intent)
            }
            chipGroup.addView(chip)
        }
    }

    fun toggle(view: View) {
        extrasVisible = !extrasVisible
        refresh()
    }
}
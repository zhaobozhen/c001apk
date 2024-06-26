package com.example.c001apk.ui.topic

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.c001apk.R
import com.example.c001apk.databinding.ActivityTopicBinding
import com.example.c001apk.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicActivity : BaseActivity<ActivityTopicBinding>() {
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = intent.getStringExtra("type")
        val title = intent.getStringExtra("title")
        val url = intent.getStringExtra("url")
        val id = intent.getStringExtra("id")

        if (supportFragmentManager.findFragmentById(R.id.topicFragment) == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.topicFragment, TopicFragment.newInstance(type, title, url, id)
                )
                .commit()
        }
    }

}
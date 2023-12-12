package com.example.c001apk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c001apk.R
import com.example.c001apk.logic.model.HomeFeedResponse
import com.example.c001apk.ui.activity.CoolPicActivity
import com.example.c001apk.ui.activity.TopicActivity
import com.example.c001apk.util.DensityTool
import com.example.c001apk.util.ImageUtil
import com.google.android.material.imageview.ShapeableImageView

class ImageSquareScrollCardAdapter(
    private val mContext: Context,
    private val imageSquareScrollCardList: List<HomeFeedResponse.Entities>
) :
    RecyclerView.Adapter<ImageSquareScrollCardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val imageSquareScrollCard: ShapeableImageView =
            view.findViewById(R.id.imageSquareScrollCard)
        var url = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_image_square_scroll_card_item, parent, false)
        val padding = 80f
        val spacePx = (DensityTool.dp2px(parent.context, padding))
        val imageWidth = DensityTool.getScreenWidth(parent.context) - spacePx
        view.layoutParams.width = (imageWidth / 5).toInt()
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(parent.context, CoolPicActivity::class.java)
            intent.putExtra("title", viewHolder.title.text.toString().replace("#", ""))
            parent.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun getItemCount() = imageSquareScrollCardList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageSquareScrollCard = imageSquareScrollCardList[position]
        holder.title.text = imageSquareScrollCard.title
        ImageUtil.showUserCover(holder.imageSquareScrollCard, imageSquareScrollCard.pic)
    }

}
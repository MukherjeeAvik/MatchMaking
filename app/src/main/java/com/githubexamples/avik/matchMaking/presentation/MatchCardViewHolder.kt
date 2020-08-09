package com.githubexamples.avik.matchMaking.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.githubexamples.avik.matchMaking.R
import com.githubexamples.avik.matchMaking.base.BaseViewHolder
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.utils.*
import kotlinx.android.synthetic.main.match_making_card_item.view.*


class MatchCardViewHolder(itemView: View) : BaseViewHolder<EachMatchCard>(itemView) {
    override fun loadData(receivedData: EachMatchCard) {

        itemView.profilePicIv.loadProfilePicture(
            receivedData.profilePic,
            itemView.context.applicationContext
        )
        itemView.profileNameTv.text = receivedData.name
        itemView.basicInfoOne.text = receivedData.age + ", " + receivedData.city
        itemView.basicInfoTwo.text = receivedData.state + ", " + receivedData.country

        itemView.acceptButton.setOnClickListener {
            itemClickCallback?.onAccepted(receivedData)
        }

        itemView.rejectButton.setOnClickListener {
            itemClickCallback?.onRejected(receivedData)
        }

        itemView.statusNowTxt.text = ""
        itemView.acceptButton.show()
        itemView.rejectButton.show()
        itemView.statusNowTxt.background = null


        if (receivedData.isInterested) {
            itemView.acceptButton.invisible()
            itemView.rejectButton.invisible()
            itemView.statusNowTxt.text = ACCEPTED
            itemView.statusNowTxt.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.accepted_green
                )
            )
            itemView.statusNowTxt.background = ResourcesCompat.getDrawable(
                itemView.context.resources,
                R.drawable.accepted_text_bg,
                null
            )
        }
        if (receivedData.hasDeclined) {
            itemView.acceptButton.invisible()
            itemView.rejectButton.invisible()
            itemView.statusNowTxt.text = REJECTED
            itemView.statusNowTxt.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.rejected_red
                )
            )
            itemView.statusNowTxt.background = ResourcesCompat.getDrawable(
                itemView.context.resources,
                R.drawable.rejected_text_bg,
                null
            )
        }


    }

    companion object {
        fun create(
            parent: ViewGroup,
            callBack: ItemClickedCallback<EachMatchCard>?
        ): MatchCardViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.match_making_card_item, parent, false)
            return MatchCardViewHolder(view).also { it.itemClickCallback = callBack }
        }
    }

}

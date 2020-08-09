package com.githubexamples.avik.matchMaking.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.githubexamples.avik.matchMaking.R
import com.githubexamples.avik.matchMaking.base.BaseViewHolder
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.utils.hide
import com.githubexamples.avik.matchMaking.utils.loadProfilePicture
import com.githubexamples.avik.matchMaking.utils.show
import kotlinx.android.synthetic.main.match_making_card_item.view.*


class MatchCardViewHolder(itemView: View) : BaseViewHolder<EachMatchCard>(itemView) {
    override fun loadData(receivedData: EachMatchCard) {

        itemView.profilePicIv.loadProfilePicture(
            receivedData.profilePic,
            itemView.context.applicationContext
        )
        itemView.profileNameTv.text = receivedData.name
        itemView.basicInfoOne.text = receivedData.age + ", " + receivedData.city
        itemView.basicInfoTwo.text =receivedData.state + ", " + receivedData.country

        itemView.acceptButton.setOnClickListener {
            itemClickCallback?.onAccepted(receivedData)
        }

        itemView.rejectButton.setOnClickListener {
            itemClickCallback?.onRejected(receivedData)
        }

        itemView.statusNowTxt.text = ""
        itemView.acceptButton.show()
        itemView.rejectButton.show()


        if(receivedData.isInterested){
            itemView.acceptButton.hide()
            itemView.rejectButton.hide()
            itemView.statusNowTxt.text = "LIKED"
        }
        if(receivedData.hasDeclined){
            itemView.acceptButton.hide()
            itemView.rejectButton.hide()
            itemView.statusNowTxt.text = "REJECTED"
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

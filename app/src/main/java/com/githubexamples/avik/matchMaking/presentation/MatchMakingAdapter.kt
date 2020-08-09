package com.githubexamples.avik.matchMaking.presentation

import android.view.ViewGroup
import com.githubexamples.avik.matchMaking.base.BaseAdapter
import com.githubexamples.avik.matchMaking.base.BaseViewHolder
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.presentation.MatchCardViewHolder
import com.githubexamples.avik.matchMaking.utils.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class MatchMakingAdapter @Inject constructor(private val scheduler: SchedulerProvider) :
    BaseAdapter<EachMatchCard, MatchCardViewHolder>() {

    private var callback_: BaseViewHolder.ItemClickedCallback<EachMatchCard>? = null

    fun registerForCallbacks(callback: BaseViewHolder.ItemClickedCallback<EachMatchCard>?) {
        callback_ = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchCardViewHolder {
        return MatchCardViewHolder.create(parent, callback_)
    }

    override fun onBindViewHolder(holder: MatchCardViewHolder, position: Int) {
        holder.loadData(list[position])
    }

    override fun getItemCount() = listSize

    fun updatePreference(userId: String, hasDeclined: Boolean, hasAccepted: Boolean) {
        val disposable = mapPreferences(userId, hasDeclined, hasAccepted)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                if (it != -1) {
                    notifyItemChanged(it)
                }
            }, {
                it.printStackTrace()
            })


    }

    private fun mapPreferences(
        userId: String,
        hasDeclined: Boolean,
        hasAccepted: Boolean
    ): Single<Int> {
        return Single.create { emitter ->
            var updatedIndex = -1
            for (i in 0 until listSize) {
                val item = list[i]
                if (item.id == userId) {
                    val updatedItem = EachMatchCard(
                        id = item.id,
                        name = item.name,
                        profilePic = item.profilePic,
                        age = item.age,
                        city = item.city,
                        state = item.state,
                        country = item.country,
                        isInterested = hasAccepted,
                        hasDeclined = hasDeclined
                    )
                    list[i] = updatedItem
                    updatedIndex = i
                    break
                }
            }
            emitter.onSuccess(updatedIndex)
        }
    }
}

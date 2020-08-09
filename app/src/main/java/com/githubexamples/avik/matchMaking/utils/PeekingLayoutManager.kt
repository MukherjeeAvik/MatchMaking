package com.githubexamples.avik.matchMaking.utils

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PeekingLayoutManager : LinearLayoutManager {
    @JvmOverloads
    constructor(
        context: Context?,
        @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
        reverseLayout: Boolean = false,
        scale: Float = 0.85f
    ) : super(context, orientation, reverseLayout) {
        widthRatio = scale
        heightRatio = scale
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun generateDefaultLayoutParams() =
        scaledLayoutParams(super.generateDefaultLayoutParams())

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?) =
        scaledLayoutParams(super.generateLayoutParams(lp))

    override fun generateLayoutParams(c: Context?, attrs: AttributeSet?) =
        scaledLayoutParams(super.generateLayoutParams(c, attrs))

    private fun scaledLayoutParams(layoutParams: RecyclerView.LayoutParams) =
        layoutParams.apply {
            when (orientation) {
                HORIZONTAL -> {
                    width = (horizontalSpace * widthRatio).toInt()
                }
                VERTICAL -> height = (verticalSpace * heightRatio).toInt()
            }
        }

    private val horizontalSpace get() = width //- paddingStart - paddingEnd

    private val verticalSpace get() = height //- paddingTop - paddingBottom

    private var widthRatio = 0.85f // change to 0.7f for 70%
    private var heightRatio = 0.35f // change to 0.7f for 70%

}


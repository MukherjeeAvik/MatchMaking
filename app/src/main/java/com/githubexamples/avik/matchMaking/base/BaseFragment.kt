package com.githubexamples.avik.matchMaking.base

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.githubexamples.avik.matchMaking.base.BaseActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {


    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    @Inject
    lateinit var app: Application



    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onDetach() {
        super.onDetach()
    }

    fun notifyUserThroughMessage(message: String){
        if(activity is BaseActivity) {
            (activity as BaseActivity).onNotifyError(message)
        }
    }

    fun removeErrors(){
        if(activity is BaseActivity) {
            (activity as BaseActivity).removeErrorsIfAny()
        }
    }

    abstract fun getFragmentTag(): String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(getLayoutId(), container, false)


    }

    abstract fun getLifeCycleObserver(): LifecycleObserver

    override fun onResume() {
        super.onResume()
        lifecycle.addObserver(getLifeCycleObserver())

    }

    override fun onPause() {
        super.onPause()
        lifecycle.removeObserver(getLifeCycleObserver())
    }



    protected fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

    }

    abstract fun reloadPage()



    interface Callback {
        fun onNotifyError(errorMessage: String)
        fun removeErrorsIfAny()
    }
}

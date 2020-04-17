package ru.gc986.simplenotebook.p.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.gc986.simplenotebook.m.DataCenterI
import javax.inject.Inject

abstract class CommonPresImpl<T : CommonView> : CommonPres<T> {

    private var view: T? = null
    private val unsubscribe = CompositeDisposable()
    @Inject lateinit var dataCenter: DataCenterI

    abstract fun init()

    protected fun getV() = view

    override fun setup(view: T) {
        this.view = view
        init()
    }

    override fun onDestroy() {
        unsubscribe.dispose()
    }

    override fun onPause() {
    }

    override fun onStart() {
    }

    fun Disposable.addToUnsubscribe() = unsubscribe.add(this)

    protected fun getNet() = dataCenter.getNetProvider()

    protected fun getSP() = dataCenter.getSharedPref()

}
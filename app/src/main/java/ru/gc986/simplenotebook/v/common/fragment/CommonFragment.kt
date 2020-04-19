package ru.gc986.simplenotebook.v.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.Unbinder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.gc986.logs.Logs
import ru.gc986.simplenotebook.p.common.CommonPres
import ru.gc986.simplenotebook.v.common.activity.CommonActivity
import javax.inject.Inject

abstract class CommonFragment<T : CommonPres<*>> : Fragment(), CommonFragmentView {

    @Inject
    lateinit var pres: T
    var logs: Logs = Logs()
    protected var butterKnifeUnbinder: Unbinder? = null
    private var presHasBeenUsed = false
    private val unsubscribe = CompositeDisposable()

    abstract override fun getLayoutId(): Int

    fun getP(): T {
        presHasBeenUsed = true
        return this.pres
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
        if (presHasBeenUsed)
            pres.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe.dispose()
        getAppActivity().hideProgress()
        if (presHasBeenUsed)
            pres.onDestroy()
        butterKnifeUnbinder?.unbind()
    }

    abstract protected fun init()

    protected fun getAppActivity() = activity as CommonActivity<*>

    fun Disposable.addToUnsubscribe() = unsubscribe.add(this)

    override fun showProgress() {
        getAppActivity().showProgress()
    }

    override fun hideProgress() {
        getAppActivity().hideProgress()
    }

    override fun showSnackBar(text: String) {
        getAppActivity().showSnackBar(text)
    }

}
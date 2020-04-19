package ru.gc986.simplenotebook.v.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.Unbinder
import ru.gc986.logs.Logs
import ru.gc986.simplenotebook.p.common.CommonPres
import javax.inject.Inject

abstract class CommonFragment<T : Any> : Fragment(), CommonFragmentView {

    @Inject
    lateinit var pres: T
    var logs: Logs = Logs()
    protected var butterKnifeUnbinder: Unbinder? = null
    private var presHasBeenUsed = false

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
            (pres as CommonPres<*>).onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presHasBeenUsed)
            (pres as CommonPres<*>).onDestroy()

        butterKnifeUnbinder?.unbind()
    }

    abstract protected fun init()

}
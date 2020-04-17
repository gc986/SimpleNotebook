package ru.gc986.simplenotebook.v.common.fragment

import androidx.fragment.app.Fragment
import ru.gc986.logs.Logs
import javax.inject.Inject

abstract class CommonFragment<T : Any> : Fragment(), CommonFragmentView {

    @Inject
    lateinit var pres: T
    var logs: Logs = Logs()

    abstract override fun getLayoutId(): Int

}
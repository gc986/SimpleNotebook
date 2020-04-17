package ru.gc986.simplenotebook.v.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import ru.gc986.logs.Logs
import javax.inject.Inject

abstract class CommonActivity<T : Any> : AppCompatActivity(), CommonActivityView {

    @Inject
    lateinit var pres: T

    abstract override @LayoutRes fun getLayoutId(): Int

    private val logs: Logs = Logs()

    fun getP(): T = this.pres

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        init()
        initView()
    }

    abstract protected fun initView()
    abstract protected fun init()

}
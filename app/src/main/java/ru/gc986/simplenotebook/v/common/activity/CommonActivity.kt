package ru.gc986.simplenotebook.v.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.gc986.logs.Logs
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.p.common.CommonPres
import ru.gc986.simplenotebook.v.common.Dialogs
import javax.inject.Inject

abstract class CommonActivity<T : CommonPres<*>> : AppCompatActivity(), CommonActivityView {

    @Inject
    lateinit var pres: T
    private var dialogs : Dialogs?  = null

    abstract override @LayoutRes fun getLayoutId(): Int

    private val logs: Logs = Logs()

    fun getP(): T = this.pres

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        dialogs = Dialogs(this)
        init()
        initView()
    }

    override fun onStop() {
        super.onStop()

        pres.onDestroy()
    }

    abstract protected fun initView()
    abstract protected fun init()

    override fun showProgress() {
        dialogs?.showProgressBar()
    }

    override fun hideProgress() {
        dialogs?.hideProgressBar()
    }

    override fun showSnackBar(text: String) {
        Snackbar.make(this.findViewById(R.id.root), text, Snackbar.LENGTH_LONG).show()
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(fragment.toString())
            .add(R.id.root, fragment)
            .commit()
    }

    fun toBack(){
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 1)
            fm.popBackStack()
        else
            finish()
    }

}
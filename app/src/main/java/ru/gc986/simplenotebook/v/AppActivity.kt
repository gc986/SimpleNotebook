package ru.gc986.simplenotebook.v

import androidx.fragment.app.Fragment
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.SimpleNotebookApp.Companion.diPres
import ru.gc986.simplenotebook.p.app.AppPI
import ru.gc986.simplenotebook.p.app.AppVI
import ru.gc986.simplenotebook.v.common.activity.CommonActivity

class AppActivity : CommonActivity<AppPI>(),
    AppVI {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        addFragment(UsersFragment.newInstance())
    }

    override fun init() {
        diPres.inject(this)
        getP().setup(this)
    }

}

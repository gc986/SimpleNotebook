package ru.gc986.simplenotebook.v

import butterknife.ButterKnife
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.SimpleNotebookApp.Companion.diPres
import ru.gc986.simplenotebook.p.main.MainPI
import ru.gc986.simplenotebook.p.main.MainVI
import ru.gc986.simplenotebook.v.common.activity.CommonActivity

class MainActivity : CommonActivity<MainPI>(),
    MainVI {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun init() {
        ButterKnife.bind(this)
        diPres.inject(this)
        getP().setup(this)
    }



}

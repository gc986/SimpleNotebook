package ru.gc986.simplenotebook.p.main

import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.common.CommonPresImpl

class MainP: CommonPresImpl<MainVI>(),
    MainPI {

    override fun init() {
        SimpleNotebookApp.diData.inject(this)
    }
}
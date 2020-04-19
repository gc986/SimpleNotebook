package ru.gc986.simplenotebook.p.app

import ru.gc986.simplenotebook.SimpleNotebookApp.Companion.diData
import ru.gc986.simplenotebook.p.common.CommonPresImpl

class AppP: CommonPresImpl<AppVI>(),
    AppPI {

    override fun init() {
        diData.inject(this)
    }
}
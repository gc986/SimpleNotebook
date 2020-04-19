package ru.gc986.simplenotebook.p.user

import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.common.CommonPresImpl

class UserP: CommonPresImpl<UserVI>(),
    UserPI {

    override fun init() {
        SimpleNotebookApp.diData.inject(this)
    }

}
package ru.gc986.simplenotebook.p.users

import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.common.CommonPresImpl

class UsersP: CommonPresImpl<UsersVI>(),
    UsersPI {
    override fun init() {
        SimpleNotebookApp.diData.inject(this)
    }
}
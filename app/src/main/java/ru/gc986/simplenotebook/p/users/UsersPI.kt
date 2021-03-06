package ru.gc986.simplenotebook.p.users

import ru.gc986.simplenotebook.p.common.CommonPres

interface UsersPI: CommonPres<UsersVI> {

    fun toUpdateUsers()
    fun toSearchUser()
    fun resetSearchUser()
    fun showUser()

}
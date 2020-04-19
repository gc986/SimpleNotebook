package ru.gc986.simplenotebook.p.users

import ru.gc986.models.user.User
import ru.gc986.simplenotebook.p.common.CommonView

interface UsersVI: CommonView {

    var userPattern: String
    var currentUser: User

    fun updateUserList(users:List<User>)
    fun showUser(user:User)

}
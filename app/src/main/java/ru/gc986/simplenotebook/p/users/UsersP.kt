package ru.gc986.simplenotebook.p.users

import io.reactivex.Observable
import ru.gc986.logs.Logs
import ru.gc986.models.Consts.Companion.ONE_SEC_IN_MILLISECONDS
import ru.gc986.models.Consts.Companion.SP_PAST_UPDATE_TIME
import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.common.CommonPresImpl

class UsersP: CommonPresImpl<UsersVI>(),
    UsersPI {

    override fun init() {
        SimpleNotebookApp.diData.inject(this)

        getSP()
            .getSPLong(SP_PAST_UPDATE_TIME)
            .subscribe({
                if (it < targerTime())
                    getUsers()
            },{
                getV().showSnackBar(it.message.toString())
            }).addToUnsubscribe()
    }

    private fun targerTime() = System.currentTimeMillis() - ONE_SEC_IN_MILLISECONDS

    private fun getUsers(){
        dataCenter.setMainServerUrl("https://raw.githubusercontent.com")

        val urls = listOf(
            "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/generated-01.json",
            "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/generated-02.json",
            "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/generated-03.json")

        getDB().deleteAllUsers()
            .flatMap { Observable.fromIterable(urls) }
            .doOnSubscribe { getV().showProgress() }
            .flatMap { getNet().getUserList(it) }
            .flatMap { getDB().insertAllUsers(it) }
            .doFinally { getV().hideProgress() }
            .subscribe({
            },{
                getV().showSnackBar(it.message.toString())
                it.printStackTrace()
            },{
                usersHasBeenUpdated()
            })
            .addToUnsubscribe()
    }

    private fun usersHasBeenUpdated(){
        getV().showSnackBar("Users updated")
        updateTime()
    }

    private fun updateTime(){
        getSP()
            .putSPLong(SP_PAST_UPDATE_TIME, System.currentTimeMillis())
            .subscribe({},{getV().showSnackBar(it.message.toString())})
            .addToUnsubscribe()
    }

    override fun toSearchUser(userPattern: String) {

    }

    override fun resetSearchUser() {

    }

}
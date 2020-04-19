package ru.gc986.simplenotebook.p.users

import io.reactivex.Observable
import ru.gc986.models.Consts.Companion.ONE_SEC_IN_MILLISECONDS
import ru.gc986.models.Consts.Companion.SP_PAST_UPDATE_TIME
import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.SimpleNotebookApp.Companion.diData
import ru.gc986.simplenotebook.p.common.CommonPresImpl

class UsersP: CommonPresImpl<UsersVI>(),
    UsersPI {

    override fun init() {
        diData.inject(this)

        getSP()
            .getSPLong(SP_PAST_UPDATE_TIME)
            .subscribe({
                if (it < targerTime())
                    toUpdateUsers()
                else
                    showUsers()
            },{
                getV().showSnackBar(it.message.toString())
            }).addToUnsubscribe()
    }

    private fun targerTime() = System.currentTimeMillis() - ONE_SEC_IN_MILLISECONDS

    override fun toUpdateUsers(){
        dataCenter.setMainServerUrl("https://raw.githubusercontent.com")

        val urls = listOf(
            "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/generated-01.json",
            "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/generated-02.json",
            "https://raw.githubusercontent.com/SkbkonturMobile/mobile-test-droid/master/json/generated-03.json")

        getDB().deleteAllUsers()
            .concatMap { Observable.fromIterable(urls) }
            .doOnSubscribe { getV().showProgress() }
            .concatMap { getNet().getUserList(it) }
            .concatMap { getDB().insertAllUsers(it) }
            .doFinally { getV().hideProgress() }
            .subscribe({
            },{
                getV().showSnackBar(it.message.toString())
                it.printStackTrace()
            },{
                showUsers()
                updateTime()
            })
            .addToUnsubscribe()
    }

    private fun showUsers(){
        getDB().getAllUsers().subscribe({
            getV().updateUserList(it)
        },{
            getV().showSnackBar(it.message.toString())
        }).addToUnsubscribe()
    }

    private fun updateTime(){
        getSP()
            .putSPLong(SP_PAST_UPDATE_TIME, System.currentTimeMillis())
            .subscribe({},{getV().showSnackBar(it.message.toString())})
            .addToUnsubscribe()
    }

    override fun toSearchUser() {
        getDB().searchUser(getV().userPattern)
            .subscribe({getV().updateUserList(it)},{ getV().showSnackBar(it.message.toString()) })
            .addToUnsubscribe()
    }

    override fun resetSearchUser() {
        showUsers()
    }

    override fun showUser() {
        getV().showUser(getV().currentUser)
    }

}
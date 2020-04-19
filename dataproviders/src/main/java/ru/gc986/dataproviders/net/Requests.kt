package ru.gc986.dataproviders.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url
import ru.gc986.models.user.User

interface Requests {

    @GET()
    fun getUserList(@Url url: String):Observable<List<User>>

}
package ru.gc986.simplenotebook.di.p

import dagger.Component
import ru.gc986.simplenotebook.p.app.AppP
import ru.gc986.simplenotebook.p.user.UserP
import ru.gc986.simplenotebook.p.users.UsersP
import javax.inject.Singleton

@Singleton
@Component(modules = [DIDataModule::class])
interface DIData {

    fun inject(pres: AppP)
    fun inject(pres: UsersP)
    fun inject(pres: UserP)

}
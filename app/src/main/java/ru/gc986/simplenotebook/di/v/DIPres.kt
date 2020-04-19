package ru.gc986.simplenotebook.di.v

import dagger.Component
import ru.gc986.simplenotebook.v.MainActivity
import ru.gc986.simplenotebook.v.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [DIPresModule::class])
interface DIPres {

    fun inject(activity: MainActivity)
    fun inject(fragment: UsersFragment)

}
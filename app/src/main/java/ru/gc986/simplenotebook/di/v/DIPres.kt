package ru.gc986.simplenotebook.di.v

import dagger.Component
import ru.gc986.simplenotebook.v.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [DIPresModule::class])
interface DIPres {

    fun inject(activity: MainActivity)

}
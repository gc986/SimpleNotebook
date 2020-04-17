package ru.gc986.simplenotebook.di.p

import dagger.Component
import ru.gc986.simplenotebook.p.main.MainP
import javax.inject.Singleton

@Singleton
@Component(modules = [DIDataModule::class])
interface DIData {

    fun inject(pres: MainP)

}
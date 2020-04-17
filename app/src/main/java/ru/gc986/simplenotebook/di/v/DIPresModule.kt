package ru.gc986.simplenotebook.di.v

import dagger.Module
import dagger.Provides
import ru.gc986.simplenotebook.p.main.MainP
import ru.gc986.simplenotebook.p.main.MainPI

@Module
class DIPresModule {

    @Provides fun provideMainP():MainPI = MainP()

}
package ru.gc986.simplenotebook.di.v

import dagger.Module
import dagger.Provides
import ru.gc986.simplenotebook.p.app.AppP
import ru.gc986.simplenotebook.p.app.AppPI
import ru.gc986.simplenotebook.p.users.UsersP
import ru.gc986.simplenotebook.p.users.UsersPI

@Module
class DIPresModule {

    @Provides fun provideMainP():AppPI = AppP()
    @Provides fun provideUsersP():UsersPI = UsersP()

}
package ru.gc986.simplenotebook.di.v

import dagger.Module
import dagger.Provides
import ru.gc986.simplenotebook.p.main.MainP
import ru.gc986.simplenotebook.p.main.MainPI
import ru.gc986.simplenotebook.p.users.UsersP
import ru.gc986.simplenotebook.p.users.UsersPI

@Module
class DIPresModule {

    @Provides fun provideMainP():MainPI = MainP()
    @Provides fun provideUsersP():UsersPI = UsersP()

}
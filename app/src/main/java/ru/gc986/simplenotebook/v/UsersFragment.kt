package ru.gc986.simplenotebook.v

import butterknife.ButterKnife
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.users.UsersPI
import ru.gc986.simplenotebook.p.users.UsersVI
import ru.gc986.simplenotebook.v.common.fragment.CommonFragment

class UsersFragment : CommonFragment<UsersPI>(),
    UsersVI {

    override fun getLayoutId(): Int = R.layout.fragment_users

    override fun init() {
        view?.let{view ->
            butterKnifeUnbinder = ButterKnife.bind(this, view)
        }
        SimpleNotebookApp.diPres.inject(this)
        getP().setup(this)
    }



}

package ru.gc986.simplenotebook.v

import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.toolbar_users.*
import ru.gc986.models.Consts.Companion.EDITTEXT_DEBOUNCE
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.users.UsersPI
import ru.gc986.simplenotebook.p.users.UsersVI
import ru.gc986.simplenotebook.v.common.fragment.CommonFragment
import ru.gc986.simplenotebook.v.common.onRightDrawableClicked
import java.util.concurrent.TimeUnit

class UsersFragment : CommonFragment<UsersPI>(),
    UsersVI {

    override fun getLayoutId(): Int = R.layout.fragment_users

    override fun init() {
        view?.let{view ->
            butterKnifeUnbinder = ButterKnife.bind(this, view)
        }
        SimpleNotebookApp.diPres.inject(this)
        getP().setup(this)

        initFindUser()

    }

    private fun initFindUser(){
        RxTextView.textChangeEvents(etUserName)
            .skipInitialValue()
            .debounce(EDITTEXT_DEBOUNCE, TimeUnit.MILLISECONDS)
            .map {
                if (it.text().isEmpty()) getP().resetSearchUser()
                it.text().toString() }
            .distinct()
            .subscribe ({ getP().toSearchUser(it) },{it.printStackTrace()})
            .addToUnsubscribe()

        etUserName.onRightDrawableClicked { it.text.clear() }
    }

}

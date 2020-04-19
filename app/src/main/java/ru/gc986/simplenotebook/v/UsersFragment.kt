package ru.gc986.simplenotebook.v

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.ButterKnife
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.toolbar_users.*
import ru.gc986.models.Consts.Companion.EDITTEXT_DEBOUNCE
import ru.gc986.models.user.User
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.SimpleNotebookApp
import ru.gc986.simplenotebook.p.users.UsersPI
import ru.gc986.simplenotebook.p.users.UsersVI
import ru.gc986.simplenotebook.v.common.adapters.UserAdapter
import ru.gc986.simplenotebook.v.common.fragment.CommonFragment
import ru.gc986.simplenotebook.v.common.onRightDrawableClicked
import java.util.concurrent.TimeUnit

class UsersFragment : CommonFragment<UsersPI>(),
    UsersVI,
    SwipeRefreshLayout.OnRefreshListener {

    override var userPattern: String = ""
    override lateinit var currentUser: User

    companion object {
        @JvmStatic
        fun newInstance() =
            UsersFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_users

    override fun init() {
        view?.let { view ->
            butterKnifeUnbinder = ButterKnife.bind(this, view)
        }
        swContainer.setOnRefreshListener(this)
        SimpleNotebookApp.diPres.inject(this)
        getP().setup(this)

        initFindUser()
    }

    private fun initFindUser() {
        RxTextView.textChangeEvents(etUserName)
            .skipInitialValue()
            .debounce(EDITTEXT_DEBOUNCE, TimeUnit.MILLISECONDS)
            .map {
                if (it.text().isEmpty()) getP().resetSearchUser()
                it.text().toString()
            }
            .subscribe({
                userPattern = it
                getP().toSearchUser()
            }, { it.printStackTrace() })
            .addToUnsubscribe()

        etUserName.onRightDrawableClicked { toClearSearch() }
        etUserName.text.clear()
    }

    private fun toClearSearch(){
        etUserName.text.clear()
        getP().resetSearchUser()
    }

    override fun updateUserList(users: List<User>) {
        rvList.adapter?.let {
            (it as UserAdapter).clear()
        }
        context?.let { context ->
            rvList.layoutManager = LinearLayoutManager(context)
            rvList.adapter = UserAdapter(context, ArrayList(users)){
                currentUser = it
                getP().showUser()
            }
        }
    }

    override fun onRefresh() {
        swContainer.isRefreshing = false
        getP().toUpdateUsers()
    }

    override fun showUser(user:User){
        getAppActivity().addFragment(UserFragment.newInstance(user))
    }

}

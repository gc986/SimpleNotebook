package ru.gc986.simplenotebook.v

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_user.*
import ru.gc986.models.Consts.Companion.USER
import ru.gc986.models.user.User
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.p.user.UserPI
import ru.gc986.simplenotebook.p.user.UserVI
import ru.gc986.simplenotebook.v.common.fragment.CommonFragment
import java.text.SimpleDateFormat


class UserFragment : CommonFragment<UserPI>(),
    UserVI {

    companion object {
        @JvmStatic
        fun newInstance(user: User) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USER, user)
                }
            }
    }

    private val DATE_DELIMITER = "T"
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(USER) as User
        }
    }

    @OnClick(R.id.llRoot)
    fun clickLlRoor() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_user

    override fun init() {
        view?.let { view ->
            butterKnifeUnbinder = ButterKnife.bind(this, view)
        }

        tvName.text = user.name
        tvPhone.text = user.phone
        tvTemperament.text = user.temperament
        tvEducationPeriod.text = "${reformatDate(user.educationPeriod.start)} - ${reformatDate(user.educationPeriod.end)}"
        tvBiography.text = user.biography

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
            getAppActivity().toBack()
        }
    }

    private fun reformatDate(input: String): String{
        val dateFrom = input.split(DATE_DELIMITER)[0]
        val formatterFrom = SimpleDateFormat("yyyy-MM-dd")
        val dateOriginal = formatterFrom.parse(dateFrom)
        val formatterTo = SimpleDateFormat("dd-MM-yyyy")
        return formatterTo.format(dateOriginal)
    }

    @OnClick(R.id.tvPhone)
    fun clickTvPhone(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${tvPhone.text}")
        startActivity(intent)
    }

}

package ru.gc986.simplenotebook.v.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gc986.models.user.User
import ru.gc986.simplenotebook.R
import ru.gc986.simplenotebook.v.common.onSelectUser

class UserAdapter(
    context: Context,
    private val users: ArrayList<User>,
    private val onSelect:onSelectUser
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun clear(){
        users.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(inflater.inflate(R.layout.adapter_user, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.tvUserName.setText(user.name)
        holder.tvUserHeight.setText(user.height.toString())
        holder.tvUserPhone.setText(user.phone)
        holder.llRoot.setOnClickListener {
            onSelect(user)
        }
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal val tvUserName: TextView = view.findViewById(R.id.tvUserName)
        internal val tvUserHeight: TextView = view.findViewById(R.id.tvUserHeight)
        internal val tvUserPhone: TextView = view.findViewById(R.id.tvUserPhone)
        internal val llRoot: LinearLayout = view.findViewById(R.id.llRoot)
    }

}
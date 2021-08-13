package ge.dbera17.finalproject.userClasses.searchedUserClasses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.dbera17.finalproject.R
import ge.dbera17.finalproject.userClasses.User

class UsersListAdapter(private var list: ArrayList<User>): RecyclerView.Adapter<UserSearchCellViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchCellViewHolder {
        return UserSearchCellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_search_cell, parent,false))
    }

    lateinit var userCellEventListener: UserSearchCellInterface
    override fun onBindViewHolder(holder: UserSearchCellViewHolder, position: Int) {
        val currUser = list[position]
        holder.name.text = currUser.nickname
        holder.profession.text = currUser.profession
        if(currUser.image != null) {
            holder.image.setImageBitmap(currUser.image)
        }

        holder.itemView.setOnClickListener {
            userCellEventListener.userClicked(currUser)
        }
    }
}

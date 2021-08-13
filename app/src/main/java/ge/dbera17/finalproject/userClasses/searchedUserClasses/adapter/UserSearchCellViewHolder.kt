package ge.dbera17.finalproject.userClasses.searchedUserClasses.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.dbera17.finalproject.R

class UserSearchCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var image: ImageView = itemView.findViewById(R.id.usc_image)
    var name: TextView = itemView.findViewById(R.id.usc_name)
    var profession: TextView = itemView.findViewById(R.id.usc_profession)
}
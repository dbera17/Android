package ge.dbera17.finalproject.pages

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ge.dbera17.finalproject.R
import ge.dbera17.finalproject.interfaces.searchedUsersInterfaces.SearchedUserMediatorInterface
import ge.dbera17.finalproject.userClasses.User
import ge.dbera17.finalproject.userClasses.searchedUserClasses.SearchedUserMediator
import ge.dbera17.finalproject.userClasses.searchedUserClasses.adapter.UserSearchCellInterface
import ge.dbera17.finalproject.userClasses.searchedUserClasses.adapter.UsersListAdapter
import java.util.*

class SearchPage : AppCompatActivity(), SearchedUserMediatorInterface, UserSearchCellInterface {

    private lateinit var foundUsersList : RecyclerView
    private lateinit var mediator: SearchedUserMediator
    private lateinit var backBtn: ImageButton
    private lateinit var searchInput: TextInputEditText

    private lateinit var allUsers: ArrayList<User>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_page)

        mediator = SearchedUserMediator(this)
        progressBar = findViewById(R.id.sp_progress_bar)
        foundUsersList = findViewById(R.id.sp_users_list)

        setUpAdapter()
        setUpSearchInputAndListener()
        setUpBackButtonAndListeners()
        fetchUsers("")
    }

    private fun setUpAdapter() {
        allUsers = arrayListOf()
        val adapter = UsersListAdapter(allUsers)
        adapter.userCellEventListener = this
        foundUsersList.adapter = adapter
    }

    private fun setUpSearchInputAndListener() {
        searchInput = findViewById(R.id.sp_search_input)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                fetchUsers(searchInput.text.toString())
            }
        })
    }

    private fun fetchUsers(nickname: String){
        progressBar.visibility = View.VISIBLE;
        if(nickname.length < 3){
            mediator.getUsers()
        }else{
            mediator.getSearchedUsers(nickname)
        }
    }

    private fun setUpBackButtonAndListeners() {
        backBtn = findViewById(R.id.sp_back)
        backBtn.setOnClickListener {
            val mainPageInt = Intent(this, MainPage::class.java)
            startActivity(mainPageInt)
        }
    }

    private fun updateUsersList(users: MutableMap<String, User>){
        allUsers.removeAll(allUsers)
        if(users.isEmpty()){
            Toast.makeText(this, "No one found!", Toast.LENGTH_SHORT).show()
        }
        for(curUser in users){
            allUsers.add(curUser.value)
        }
        foundUsersList.adapter?.notifyDataSetChanged()
        progressBar.visibility = View.INVISIBLE;
    }

    override fun notifyViewForUpdate(users: MutableMap<String, User>) {
        updateUsersList(users)
    }
    override fun userClicked(user: User) {
        Toast.makeText(this, "chat with ${user.nickname}", Toast.LENGTH_SHORT).show()
    }
}
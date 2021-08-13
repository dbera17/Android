package ge.dbera17.finalproject.userClasses.searchedUserClasses

import android.graphics.BitmapFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.storage
import ge.dbera17.finalproject.interfaces.searchedUsersInterfaces.SearchedUserInterface
import ge.dbera17.finalproject.userClasses.User

class SearchedUser(private val mediator: SearchedUserInterface) {
    fun getUsers(){
        val currentUser = Firebase.auth.currentUser?.email.toString()
        val nickname = currentUser.removeSuffix("@gmail.com")
        val users = mutableMapOf<String, User>()

        Firebase.database.reference.child("users").get().addOnSuccessListener { result ->
            val usersInfo = result.value as HashMap<*, *>
            for ((key, value) in usersInfo) {
                getAllUsers(key, value, nickname, users)
            }
            // Fetching Images For All Users
            Firebase.storage.reference.listAll().addOnSuccessListener { (fetchedItems) ->
                fetchedItems.forEach { storageRef ->
                    storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { result ->
                        val currUserNickname = storageRef.name
                        users[currUserNickname]?.image = BitmapFactory.decodeByteArray(result, 0, result.size)
                        mediator.onGetUsersAPISuccess(users)
                    }
                }
            }
        }
    }

    fun getSearchedUsers(userToSearch: String){
        val currentUser = Firebase.auth.currentUser?.email.toString()
        val nickname = currentUser.removeSuffix("@gmail.com")
        val users = mutableMapOf<String, User>()

        Firebase.database.reference.child("users").get().addOnSuccessListener { result ->
            val usersInfo = result.value as HashMap<*, *>
            for ((key, value) in usersInfo) {
                getAllSearchedUsers(key, value, nickname, users, userToSearch)
            }
            // Fetching Images For Searched Users
            Firebase.storage.reference.listAll().addOnSuccessListener { (fetchedItems) ->
                fetchedItems.forEach { storageRef ->
                    storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { result ->
                        val currUserNickname = storageRef.name
                        users[currUserNickname]?.image = BitmapFactory.decodeByteArray(result, 0, result.size)
                        mediator.onGetSearchedUsersAPISuccess(users)
                    }
                }
            }
        }
    }

    private fun getAllSearchedUsers(
        key: Any?,
        value: Any?,
        nickname: String,
        users: MutableMap<String, User>,
        searchedString: String
    ) {
        if(key != nickname && key.toString().contains(searchedString)) {
            value as HashMap<*, *>
            val fountUser = User(
                value["nickname"].toString(),
                value["password"].toString(),
                value["profession"].toString()
            )
            users[value["nickname"].toString()] = fountUser
        }
    }

    private fun getAllUsers(
        key: Any?,
        value: Any?,
        nickname: String,
        users: MutableMap<String, User>,
    ) {
        if(key != nickname) {
            value as HashMap<*, *>
            val fountUser = User(
                value["nickname"].toString(),
                value["password"].toString(),
                value["profession"].toString()
            )
            users[value["nickname"].toString()] = fountUser
        }
    }
}
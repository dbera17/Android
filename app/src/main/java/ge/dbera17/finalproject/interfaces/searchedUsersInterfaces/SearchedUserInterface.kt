package ge.dbera17.finalproject.interfaces.searchedUsersInterfaces

import ge.dbera17.finalproject.userClasses.User

interface SearchedUserInterface {
    fun onGetUsersAPISuccess(users: MutableMap<String, User>)
    fun onGetSearchedUsersAPISuccess(users: MutableMap<String, User>)
}
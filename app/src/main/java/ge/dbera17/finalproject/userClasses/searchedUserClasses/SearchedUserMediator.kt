package ge.dbera17.finalproject.userClasses.searchedUserClasses

import ge.dbera17.finalproject.userClasses.User
import ge.dbera17.finalproject.interfaces.searchedUsersInterfaces.SearchedUserInterface
import ge.dbera17.finalproject.interfaces.searchedUsersInterfaces.SearchedUserMediatorInterface

class SearchedUserMediator (private val view: SearchedUserMediatorInterface): SearchedUserInterface {

    private val mediator = SearchedUser(this)
    fun getUsers() {
        mediator.getUsers()
    }
    fun getSearchedUsers(userToSearch: String) {
        mediator.getSearchedUsers(userToSearch)
    }
    override fun onGetUsersAPISuccess(users: MutableMap<String, User>) {
        view.notifyViewForUpdate(users)
    }
    override fun onGetSearchedUsersAPISuccess(users: MutableMap<String, User>) {
        view.notifyViewForUpdate(users)
    }
}
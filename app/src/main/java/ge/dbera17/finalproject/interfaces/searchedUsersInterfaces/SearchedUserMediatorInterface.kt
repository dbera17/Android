package ge.dbera17.finalproject.interfaces.searchedUsersInterfaces

import ge.dbera17.finalproject.userClasses.User

interface SearchedUserMediatorInterface {
    fun notifyViewForUpdate(users: MutableMap<String, User>)
}
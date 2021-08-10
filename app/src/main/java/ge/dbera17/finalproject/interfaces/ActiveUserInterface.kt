package ge.dbera17.finalproject.interfaces

import ge.dbera17.finalproject.userClasses.User

interface ActiveUserInterface {
    open fun onAPISuccess(user: User)
}
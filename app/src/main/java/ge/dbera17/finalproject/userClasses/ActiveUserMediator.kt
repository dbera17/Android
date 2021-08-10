package ge.dbera17.finalproject.userClasses

import android.net.Uri
import ge.dbera17.finalproject.interfaces.ActiveUserInterface
import ge.dbera17.finalproject.interfaces.UserInterface

class ActiveUserMediator(private val view: UserInterface): ActiveUserInterface {

    override fun onAPISuccess(user: User) {
        view.showUserInfo(user)
    }

    private val mediator = ActiveUser(this)
    fun getUserInfo(nickname: String){
        mediator.getUserInfo(nickname)
    }
    fun updateUserProfession(nickname: String, profession: String){
        mediator.updateUserProfession(nickname, profession)
    }
    fun updateUserNickname(nickname: String, newNickname: String){
        mediator.updateUserNickname(nickname, newNickname)
    }
    fun uploadUserImage(nickname: String, selectedImage: Uri){
        mediator.uploadUserImage(nickname, selectedImage)
    }

}
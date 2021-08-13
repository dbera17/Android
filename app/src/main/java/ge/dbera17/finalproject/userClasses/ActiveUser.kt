package ge.dbera17.finalproject.userClasses

import android.graphics.BitmapFactory
import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.database.ktx.database
import com.google.firebase.database.FirebaseDatabase

class ActiveUser(private val mediator: ActiveUserMediator) {

    private lateinit var database : FirebaseDatabase

    fun getUserInfo(nickname: String) {

        database = Firebase.database
        val db = database.getReference("users")
        val user = User()

        db.child(nickname).get().addOnSuccessListener { result ->
            val userInfo= result.value as HashMap<*, *>
            user.nickname = userInfo["nickname"].toString()
            user.password = userInfo["password"].toString()
            user.profession = userInfo["profession"].toString()

            Firebase.storage.reference.child(nickname).getBytes(Long.MAX_VALUE).addOnSuccessListener {
                user.image = BitmapFactory.decodeByteArray(it, 0, it.size)
                mediator.onAPISuccess(user)
            }.addOnFailureListener {
                user.image = null
                mediator.onAPISuccess(user)
            }
        }
    }
    fun updateUserProfession(nickname: String, profession: String){
        database = Firebase.database
        val db = database.getReference("users")
        db.child(nickname).child("profession").setValue(profession)
    }
    fun updateUserNickname(nickname: String, newNickname: String){
        database = Firebase.database
        val db = database.getReference("users")
        db.child(nickname).child("nickname").setValue(newNickname)
    }
    fun uploadUserImage(nickname: String, selectedImage: Uri){
        val storage = Firebase.storage
        val storageRef = storage.reference
        val currUserStorage = storageRef.child(nickname)
        currUserStorage.putFile(selectedImage)
    }
}
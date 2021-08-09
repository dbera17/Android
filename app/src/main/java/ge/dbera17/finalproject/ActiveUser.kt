package ge.dbera17.finalproject

import android.graphics.BitmapFactory
import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.database.ktx.database
import com.google.firebase.database.FirebaseDatabase


class ActiveUser {

    private lateinit var database : FirebaseDatabase

    fun getUserInfo(nickname: String): User {

        database = Firebase.database
        val db = database.getReference("users")
        val user = User()

        db.child(nickname).get().addOnSuccessListener {
            val userData = it.value as HashMap<*, *>

            user.nickname = userData["nickname"].toString()
            user.password = userData["password"].toString()
            user.profession = userData["profession"].toString()

            val storageReference = Firebase.storage.reference
            val imageRef = storageReference.child(nickname)

            imageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener {
                user.image = BitmapFactory.decodeByteArray(it, 0, it.size)
            }.addOnFailureListener {
                user.image = null
            }
        }
        return user
    }

    fun updateUserProfession(nickname: String, profession: String){
        database = Firebase.database
        val db = database.reference
        db.child("users").child(nickname).child("profession").setValue(profession)
    }
    fun updateUserNickname(nickname: String, newNickname: String){
        database = Firebase.database
        val db = database.reference
        db.child("users").child(nickname).child("nickname").setValue(newNickname)
    }

    fun uploadUserImage(nickname: String, selectedImage: Uri){
        val storage = Firebase.storage
        val storageRef = storage.reference
        val riversRef = storageRef.child(nickname)
        riversRef.putFile(selectedImage)
    }
}
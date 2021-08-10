package ge.dbera17.finalproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.FirebaseDatabase


class Registration : AppCompatActivity() {
    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var profession: EditText

    private lateinit var signUp: Button

    private lateinit var db : FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        setUpFirebase()
        setUpItems()
    }

    private fun setUpFirebase() {
        db = Firebase.database
        auth = Firebase.auth
    }

    private fun setUpItems() {
        signUp = findViewById(R.id.rp_sign_up)
        nickname = findViewById(R.id.rp_nickname)
        password = findViewById(R.id.rp_password)
        profession = findViewById(R.id.rp_profession)

        signUp.setOnClickListener {
            signUpNewUser()
        }
    }

    private fun signUpNewUser() {
        if(nickname.text.isNotEmpty() && password.text.isNotEmpty() && profession.text.isNotEmpty()){
            val nickname = nickname.text.toString()
            val password = password.text.toString()
            val profession = profession.text.toString()
            addUserToDB(nickname, password, profession)
        } else {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun addUserToDB(name:String, pwd:String, prof:String) {
        val users =  db.getReference("users")

        val email = "$name@gmail.com"
        auth.createUserWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    users.push().key?.let{
                        users.child(name).setValue(
                            User(name, pwd, prof, BitmapFactory.decodeResource(resources,R.drawable.profile_test))
                        )
                    }
                    val mainPageInt = Intent(this, MainPage::class.java)
                    startActivity(mainPageInt)
                } else {
                    val errorMessage = task.exception?.message.toString()
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }
}
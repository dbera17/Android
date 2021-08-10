package ge.dbera17.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.dbera17.finalproject.pages.MainPage
import ge.dbera17.finalproject.pages.Registration

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var signUp: Button
    private lateinit var signIn: Button

    private lateinit var nickname: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        signUp = findViewById(R.id.sp_sign_up)
        signIn = findViewById(R.id.sp_sign_in)
        nickname = findViewById(R.id.sp_nickname)
        password = findViewById(R.id.sp_password)
        setUpOnClickListeners()
    }

    private fun setUpOnClickListeners() {
        signUp.setOnClickListener {
            goToRegistration()
        }
        signIn.setOnClickListener {
            if (nickname.text.isNotEmpty() && password.text.isNotEmpty()) {
                val nickname = nickname.text.toString()
                val password = password.text.toString()
                signIn(nickname, password)
            }else{
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun signIn(name:String, pwd:String){
        val email = "$name@gmail.com"
        auth.signInWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val mainPageInt = Intent(this, MainPage::class.java)
                    startActivity(mainPageInt)
                } else {
                    val errorMessage = task.exception?.message.toString()
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun goToRegistration() {
        val registrationPageInt = Intent(this, Registration::class.java)
        startActivity(registrationPageInt)
    }
}
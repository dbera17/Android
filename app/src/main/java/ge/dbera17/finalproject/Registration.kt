package ge.dbera17.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Registration : AppCompatActivity() {
    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var profession: EditText

    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        setUpItems()
    }

    private fun setUpItems() {
        signUp = findViewById(R.id.rp_sign_up)
        nickname = findViewById(R.id.nickname)
        password = findViewById(R.id.password)
        profession = findViewById(R.id.profession)

        signUp.setOnClickListener {
            signUpNewUser()
        }
    }

    private fun signUpNewUser() {
        if(nickname.text.isNotEmpty() && password.text.isNotEmpty() && profession.text.isNotEmpty()){
            Toast.makeText(this, "Create User", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()
        }
    }
}
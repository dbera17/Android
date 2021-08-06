package ge.dbera17.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
class MainActivity : AppCompatActivity() {

    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signUp = findViewById(R.id.sign_up)
        signUp.setOnClickListener {
            goToRegistration()
        }
    }

    private fun goToRegistration() {
        val registrationPageInt = Intent(this, Registration::class.java)
        startActivity(registrationPageInt)
    }
}
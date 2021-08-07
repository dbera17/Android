package ge.dbera17.finalproject

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var search: TextInputLayout
    private lateinit var addBtn: FloatingActionButton
    private lateinit var homeBtn: ImageButton
    private lateinit var profileBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        search = findViewById(R.id.mp_search)
        addBtn = findViewById(R.id.mp_add)
        homeBtn = findViewById(R.id.mp_home)
        profileBtn = findViewById(R.id.mp_profile)

        setUpFirebaseAuth()
        setUpButtonListeners()
    }

    private  fun setUpFirebaseAuth() {
        auth = Firebase.auth
        val user = auth.currentUser?.email.toString()
        Toast.makeText(this, user, Toast.LENGTH_LONG).show()
    }

    private fun setUpButtonListeners() {
        search.setEndIconOnClickListener{
            Toast.makeText(this, "SEARCH", Toast.LENGTH_LONG).show()
        }
        addBtn.setOnClickListener{
            Toast.makeText(this, "GO TO SEARCH PAGE", Toast.LENGTH_LONG).show()
        }
        profileBtn.setOnClickListener {
            Toast.makeText(this, "GO TO PROFILE", Toast.LENGTH_LONG).show()
        }
    }
}
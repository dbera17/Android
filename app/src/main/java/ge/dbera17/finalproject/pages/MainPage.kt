package ge.dbera17.finalproject.pages

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.dbera17.finalproject.R

class MainPage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var search: TextInputLayout
    private lateinit var addBtn: FloatingActionButton
    private lateinit var homeBtn: ImageButton
    private lateinit var profileBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        auth = Firebase.auth
        search = findViewById(R.id.mp_search)
        addBtn = findViewById(R.id.mp_add)
        homeBtn = findViewById(R.id.mp_home)
        profileBtn = findViewById(R.id.mp_profile)

        setUpButtonListeners()
    }

    private fun setUpButtonListeners() {
        search.setEndIconOnClickListener{
            Toast.makeText(this, "SEARCH", Toast.LENGTH_LONG).show()
        }
        addBtn.setOnClickListener{
            Toast.makeText(this, "GO TO SEARCH PAGE", Toast.LENGTH_LONG).show()
        }
        profileBtn.setOnClickListener {
            val profilePageInt = Intent(this, ProfilePage::class.java)
            startActivity(profilePageInt)
        }
    }
}
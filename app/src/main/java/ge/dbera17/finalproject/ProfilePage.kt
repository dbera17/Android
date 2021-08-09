package ge.dbera17.finalproject

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class ProfilePage  : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var update: Button
    private lateinit var signOut: Button

    private lateinit var addBtn: FloatingActionButton
    private lateinit var homeBtn: ImageButton

    private lateinit var newImage: CircleImageView
    private lateinit var newNickname: EditText
    private lateinit var newProfession: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

        update = findViewById(R.id.pp_update)
        signOut = findViewById(R.id.pp_sign_out)

        addBtn = findViewById(R.id.pp_add)
        homeBtn = findViewById(R.id.pp_home)

        newImage = findViewById(R.id.pp_avatar_image)
        newNickname = findViewById(R.id.pp_nickname)
        newProfession = findViewById(R.id.pp_profession)

        setUpFirebaseAuth()
        setUpButtonListeners()
    }

    private  fun setUpFirebaseAuth() {
        auth = Firebase.auth
        val user = auth.currentUser?.email.toString()
        Toast.makeText(this, user, Toast.LENGTH_LONG).show()
    }

    private fun setUpButtonListeners() {
        addBtn.setOnClickListener{
            Toast.makeText(this, "GO TO SEARCH PAGE", Toast.LENGTH_LONG).show()
        }
        homeBtn.setOnClickListener {
            val mainPageInt = Intent(this, MainPage::class.java)
            startActivity(mainPageInt)
        }
        update.setOnClickListener {
            Toast.makeText(this, "update", Toast.LENGTH_LONG).show()
        }
        signOut.setOnClickListener {
            Toast.makeText(this, "sign out", Toast.LENGTH_LONG).show()
        }
    }
}
package ge.dbera17.finalproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView

class ProfilePage  : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var activeUser: ActiveUser
    private lateinit var userInfo: User
    private lateinit var update: Button
    private lateinit var signOut: Button

    private lateinit var addBtn: FloatingActionButton
    private lateinit var homeBtn: ImageButton

    private lateinit var newImage: CircleImageView
    private lateinit var newNickname: EditText
    private lateinit var newProfession: EditText

    private lateinit var prevNickname: String

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)

        setUpFirebaseAuth()

        update = findViewById(R.id.pp_update)
        signOut = findViewById(R.id.pp_sign_out)

        addBtn = findViewById(R.id.pp_add)
        homeBtn = findViewById(R.id.pp_home)

        newImage = findViewById(R.id.pp_avatar_image)
        newNickname = findViewById(R.id.pp_nickname)
        newProfession = findViewById(R.id.pp_profession)

        setUpButtonListeners()
        showUserInfo()
    }

    private fun showUserInfo() {
        newNickname.setText(userInfo.nickname)
        newProfession.setText(userInfo.profession)

        if (userInfo.image != null) {
            newImage.setImageBitmap(userInfo.image)
        }
    }

    private  fun setUpFirebaseAuth() {
        auth = Firebase.auth
        val user = auth.currentUser
        val userEmail = user?.email.toString()
        prevNickname = userEmail.removeSuffix("@gmail.com")
        activeUser = ActiveUser()
        userInfo = activeUser.getUserInfo(prevNickname)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImage: Uri = data?.data!!

            newImage.setImageURI(selectedImage)
            activeUser.uploadUserImage(prevNickname, selectedImage)
        }
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
            if(newProfession.length() != 0){
                activeUser.updateUserProfession(prevNickname, newProfession.text.toString())
            }
            if(newNickname.length() != 0){
                activeUser.updateUserNickname(prevNickname, newNickname.text.toString())
            }
        }
        signOut.setOnClickListener {
            Firebase.auth.signOut()
            val starterPageInt = Intent(this,MainActivity::class.java)
            startActivity(starterPageInt)
        }
        newImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }
    }
}
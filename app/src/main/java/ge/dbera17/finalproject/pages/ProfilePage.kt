package ge.dbera17.finalproject.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import ge.dbera17.finalproject.MainActivity
import ge.dbera17.finalproject.R
import ge.dbera17.finalproject.interfaces.UserInterface
import ge.dbera17.finalproject.userClasses.ActiveUserMediator
import ge.dbera17.finalproject.userClasses.User

class ProfilePage  : AppCompatActivity(), UserInterface {
    private lateinit var auth: FirebaseAuth

    private lateinit var activeUser: ActiveUserMediator
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
    }

    private  fun setUpFirebaseAuth() {
        auth = Firebase.auth
        val user = auth.currentUser
        val userEmail = user?.email.toString()
        prevNickname = userEmail.removeSuffix("@gmail.com")
        activeUser = ActiveUserMediator(this)
        activeUser.getUserInfo(prevNickname)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            if (data != null) {
                imageUri = data.data
                newImage.setImageURI(imageUri)
                activeUser.uploadUserImage(prevNickname, imageUri!!)
            }
        }
    }

    private fun setUpButtonListeners() {
        addBtn.setOnClickListener{
            val searchPageInt = Intent(this, SearchPage::class.java)
            startActivity(searchPageInt)
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
            val starterPageInt = Intent(this, MainActivity::class.java)
            startActivity(starterPageInt)
        }
        newImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun showUserInfo(user: User) {
        newNickname.setText(user.nickname)
        newProfession.setText(user.profession)

        if (user.image != null) {
            newImage.setImageBitmap(user.image)
        }
    }
}
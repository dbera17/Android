package ge.dbera17.finalproject.pages

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import ge.dbera17.finalproject.R

class SearchPage : AppCompatActivity() {

    private lateinit var backBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_page)

        backBtn = findViewById(R.id.sp_back)
        backBtn.setOnClickListener {
            val mainPageInt = Intent(this, MainPage::class.java)
            startActivity(mainPageInt)
        }
    }
}
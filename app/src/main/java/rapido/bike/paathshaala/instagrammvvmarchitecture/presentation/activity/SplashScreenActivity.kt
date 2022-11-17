package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import rapido.bike.paathshaala.instagrammvvmarchitecture.Constants.SPLASH_TIME_OUT
import rapido.bike.paathshaala.instagrammvvmarchitecture.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
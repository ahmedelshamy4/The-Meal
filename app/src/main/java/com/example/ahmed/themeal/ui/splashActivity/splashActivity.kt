package com.example.ahmed.themeal.ui.splashActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.ahmed.themeal.ui.main.MainActivity
import com.example.ahmed.themeal.R
import kotlinx.android.synthetic.main.activity_splash.*

class splashActivity : AppCompatActivity() {
    lateinit var splashTread: Thread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        StartAnimations()
    }

    private fun StartAnimations() {
        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()
        lin_lay.clearAnimation()
        lin_lay.startAnimation(anim)

        anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim.reset()
        ll.clearAnimation()
        ll.startAnimation(anim)

        splashTread = object : Thread() {
            override fun run() {
                try {
                    var waited = 0
                    // Splash screen pause time
                    while (waited < 3500) {
                        Thread.sleep(100)
                        waited += 100
                    }
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
                    this@splashActivity.finish()
                }

            }
        }
        splashTread.start()

    }
}

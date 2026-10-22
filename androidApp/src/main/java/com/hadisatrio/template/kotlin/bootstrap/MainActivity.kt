package com.hadisatrio.template.kotlin.bootstrap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hadisatrio.libs.cmp.greeting.GreetingScreen
import com.hadisatrio.libs.kmp.greeting.Greeting

/**
 * A [ComponentActivity] that shows a greeting.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingScreen(greeting = Greeting(name = "Android"))
        }
    }
}

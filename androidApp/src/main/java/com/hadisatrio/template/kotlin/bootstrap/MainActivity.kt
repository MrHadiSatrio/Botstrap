package com.hadisatrio.template.kotlin.bootstrap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hadisatrio.template.kotlin.bootstrap.greeting.Greeting
import com.hadisatrio.template.kotlin.bootstrap.greeting.GreetingScreen

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

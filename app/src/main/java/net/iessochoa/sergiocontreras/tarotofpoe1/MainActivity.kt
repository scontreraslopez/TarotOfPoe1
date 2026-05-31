package net.iessochoa.sergiocontreras.tarotofpoe1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation.NavigationRoot
import net.iessochoa.sergiocontreras.tarotofpoe1.ui.theme.TarotOfPoe1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarotOfPoe1Theme {
                NavigationRoot()
            }
        }
    }
}
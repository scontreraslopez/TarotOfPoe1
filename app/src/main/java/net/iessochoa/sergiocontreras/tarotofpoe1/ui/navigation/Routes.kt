package net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Project: TarotOfPoe1
 * From: net.iessochoa.sergiocontreras.tarotofpoe1.ui.navigation
 * Created by: Contr
 * On: 31/05/2026 at 19:34
 * Creado en Settings -> Editor -> File and Code Templates
 */
sealed class Routes: NavKey {
    @Serializable
    data object Login:Routes()

    @Serializable
    data class Home(val name:String):Routes()

    @Serializable
    data class Detail(val cardId: String):Routes()

}
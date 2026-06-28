package net.iessochoa.sergiocontreras.tarotofpoe1.data.network

import kotlinx.serialization.Serializable
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.Acquisition
import net.iessochoa.sergiocontreras.tarotofpoe1.domain.model.DivinationCard

/**
 * DTOs del endpoint `GET /cards` del Worker de Cloudflare.
 *
 * El endpoint devuelve un array JSON de cartas directamente (sin objeto
 * envoltorio), por eso [TarotOfPoeApiService.getCards] devuelve `List<CardDto>`.
 *
 * Los nombres de campo coinciden con el JSON, así que no hace falta @SerialName.
 */
@Serializable
data class CardDto(
    val id: String,
    val name: String,
    val stackSize: Int,
    val reward: String,
    val flavourText: String,
    val acquisition: AcquisitionDto,
    val artUrl: String? = null,
)

@Serializable
data class AcquisitionDto(
    val dropRestricted: Boolean,
    val dropLevel: Int,
    val areaRestrictions: List<String> = emptyList(),
)

/** Mapea el DTO de red al modelo de dominio que consumen las pantallas. */
fun CardDto.toDomain(): DivinationCard = DivinationCard(
    id = id,
    name = name,
    stackSize = stackSize,
    reward = reward,
    flavourText = flavourText,
    acquisition = acquisition.toDomain(),
    artUrl = artUrl,
)

fun AcquisitionDto.toDomain(): Acquisition = Acquisition(
    dropRestricted = dropRestricted,
    dropLevel = dropLevel,
    areaRestrictions = areaRestrictions,
)
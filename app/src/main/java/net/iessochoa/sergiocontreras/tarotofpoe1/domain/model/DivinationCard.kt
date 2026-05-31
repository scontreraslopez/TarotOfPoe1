package net.iessochoa.sergiocontreras.tarotofpoe1.domain.model

/**
 * A Path of Exile divination card.
 *
 * Modelled after the poewiki layout, e.g.:
 * https://www.poewiki.net/wiki/A_Chilling_Wind
 *
 * Used by both screens:
 *  - the browser/list (filter by [name] or by area in [acquisition].areaRestrictions)
 *  - the detail screen (full card face + acquisition info)
 */
data class DivinationCard(
    val id: String,
    val name: String,
    val stackSize: Int,
    /** Card face reward, e.g. "Level 21 Vaal Cold Snap (Corrupted, +20% Quality)". */
    val reward: String,
    /** The italic flavour quote on the card (what you called "description"). */
    val flavourText: String,
    val acquisition: Acquisition,
    /** Card art image, if available. */
    val artUrl: String? = null,
)

/**
 * Everything under the wiki's "Item acquisition" section.
 *
 * [areaRestrictions] is the "Area restrictions" sub-list, so it lives here
 * instead of being a sibling field of the card (avoids the overlap you spotted).
 */
data class Acquisition(
    /** The "Drop restricted" badge: the card only drops in specific places. */
    val dropRestricted: Boolean,
    val dropLevel: Int,
    /** Areas/maps where it can drop, e.g. ["Frozen Cabins", "Summit"]. Empty if unrestricted. */
    val areaRestrictions: List<String>,
)
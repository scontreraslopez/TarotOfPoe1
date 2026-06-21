package net.iessochoa.sergiocontreras.tarotofpoe1.domain.model

/**
 * Hard-coded sample cards for @Preview and as a fallback dataset until the
 * Firestore repository is wired. "A Chilling Wind" mirrors the real wiki page;
 * the rest use the values shown on that page (acquisition left minimal).
 */
object DivinationCardSamples {

    val aChillingWind = DivinationCard(
        id = "a-chilling-wind",
        name = "A Chilling Wind",
        stackSize = 4,
        reward = "Level 21 Vaal Cold Snap (Corrupted, +20% Quality)",
        flavourText = "All things slow under winter's onslaught - even life itself.",
        acquisition = Acquisition(
            dropRestricted = true,
            dropLevel = 72,
            areaRestrictions = listOf("Frozen Cabins", "Summit"),
        ),
    )

    val theGambler = DivinationCard(
        id = "the-gambler",
        name = "The Gambler",
        stackSize = 5,
        reward = "A random divination card",
        flavourText = "\"I don't believe in karma. If it were real, I would never win.\"",
        acquisition = Acquisition(
            dropRestricted = false,
            dropLevel = 1,
            areaRestrictions = emptyList(),
        ),
        artUrl = "https://www.poewiki.net/images/7/70/A_Chilling_Wind_card_art.png"
    )

    val theVoid = DivinationCard(
        id = "the-void",
        name = "The Void",
        stackSize = 1,
        reward = "Reach into the Void and claim your prize",
        flavourText = "Reach into the Void and claim your prize.",
        acquisition = Acquisition(
            dropRestricted = false,
            dropLevel = 1,
            areaRestrictions = emptyList(),
        ),
        artUrl = "https://www.poewiki.net/images/3/35/The_Void_card_art.png"
    )

    val all: List<DivinationCard> = listOf(aChillingWind, theGambler, theVoid)
}
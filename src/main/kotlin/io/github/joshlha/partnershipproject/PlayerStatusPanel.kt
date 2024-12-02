package io.github.joshlha.partnershipproject

import io.github.joshlha.partnershipproject.playerPosition.d
import io.github.joshlha.partnershipproject.playerPosition.f
import io.github.joshlha.partnershipproject.playerPosition.getPlayerCoords
import net.miginfocom.swing.MigLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSpinner

val player = playerCharacter(name = "Eric", health = 60, atkPoints = 50, defPoints = 40,
matkPoints = 30, mdefPoints =  10, mp = 50)
class PlayerStatusPanel: JPanel(MigLayout("Wrap")) {

    val health = player.health
    val mp = player.mp
    val playerName = player.name
    var playerCoord = JLabel(getPlayerCoords())
    init{
        add(JLabel(playerName))
        add(JLabel("Health: "+ Integer.toString(health)))
        add(JLabel("MP: "+ Integer.toString(mp)))
        add(playerCoord)
    }


}
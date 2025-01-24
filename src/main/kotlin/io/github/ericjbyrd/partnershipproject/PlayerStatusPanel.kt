package io.github.ericjbyrd.partnershipproject

import io.github.ericjbyrd.partnershipproject.playerPosition.d
import io.github.ericjbyrd.partnershipproject.playerPosition.f
import io.github.ericjbyrd.partnershipproject.playerPosition.getPlayerCoords
import io.github.ericjbyrd.partnershipproject.playerPosition.stepCounter
import jdk.internal.org.jline.utils.Colors.J
import net.miginfocom.swing.MigLayout
import javax.swing.JLabel
import javax.swing.JPanel

val player = playerCharacter(name = "Eric", health = 60, atkPoints = 50, defPoints = 40,
matkPoints = 30, mdefPoints =  10, mp = 50)
class PlayerStatusPanel: JPanel(MigLayout("Wrap")) {

    val health = player.health
    val mp = player.mp
    val playerName = player.name
    var playerCoord = JLabel(getPlayerCoords())
    var stepCounter = JLabel(stepCounter())
    init{
        add(JLabel(playerName))
        add(JLabel("Health: "+ Integer.toString(health)))
        add(JLabel("MP: "+ Integer.toString(mp)))
        add(playerCoord)
        add(stepCounter)
    }


}
package io.github.ericjbyrd.partnershipproject

import io.github.ericjbyrd.partnershipproject.PlayerPosition.getPlayerCoords
import io.github.ericjbyrd.partnershipproject.PlayerPosition.stepCounter
import net.miginfocom.swing.MigLayout
import javax.swing.JLabel
import javax.swing.JPanel

val player = playerCharacter(name = "Eric", health = 60, atkPoints = 50, defPoints = 40,
matkPoints = 30, mdefPoints =  10, mp = 50, maxMP = 50, maxHealth = 60)
class PlayerStatusPanel: JPanel(MigLayout("Wrap")) {

    var health = JLabel("Health: "+ player.health.toString())
    var mp = JLabel("MP: "+ player.mp.toString())
    val playerName = player.name
    var playerCoord = JLabel(getPlayerCoords())
    var stepCounter = JLabel(stepCounter())
    init{
        add(JLabel(playerName))
        add(health)
        add(mp)
        add(playerCoord)
        add(stepCounter)
    }


}
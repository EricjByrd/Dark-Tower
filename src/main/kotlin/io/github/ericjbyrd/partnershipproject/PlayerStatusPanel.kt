package io.github.ericjbyrd.partnershipproject

import io.github.ericjbyrd.partnershipproject.PlayerPosition.getPlayerCoords
import io.github.ericjbyrd.partnershipproject.PlayerPosition.stepCounter
import net.miginfocom.swing.MigLayout
import javax.swing.JLabel
import javax.swing.JPanel


open class StatusPanel(character: Character): JPanel(MigLayout("Wrap")) {

    open var health = JLabel("Health: "+ character.health.toString())
    open var mp = JLabel("MP: "+ character.mp.toString())
    open var name = JLabel(character.name)
}

class PlayerStatusPanel(player: Character) : StatusPanel(player) {
    override var health = JLabel("Health: " + player.health.toString())
    override var mp = JLabel("MP: " + player.mp.toString())
    val playerName = JLabel(player.name)
    var playerCoord = JLabel(getPlayerCoords())
    var stepCounter = JLabel(stepCounter())

    init {
        add(playerName)
        add(health)
        add(mp)
        add(playerCoord)
        add(stepCounter)
    }
}
class MonsterStatusPanel(monster: Monster): StatusPanel(monster) {
        override var health = JLabel("Health: "+ monster.health.toString())
        override var mp = JLabel("MP: "+ monster.mp.toString())
        val monsterName = monster.name
        init{
            add(JLabel(monsterName))
            add(health)
            add(mp)
        }
}

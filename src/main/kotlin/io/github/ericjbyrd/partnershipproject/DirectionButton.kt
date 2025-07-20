package io.github.ericjbyrd.partnershipproject

import net.miginfocom.swing.MigLayout
import java.awt.Font
import javax.swing.JButton
import javax.swing.JPanel

class ButtonPanel: JPanel(MigLayout()){

    class DirectionButton(
        text: String
    ) : JButton(text) {
        init {
            font = font.deriveFont(Font.BOLD, 20F)
        }
    }
    val upButton = DirectionButton("↑")
    val downButton = DirectionButton("↓")
    val leftButton = DirectionButton("←")
    val rightButton = DirectionButton("→")
    init{

        add(upButton,"dock north, wrap, grow")
        add(leftButton,"dock west, grow")
        add(rightButton, "dock east, wrap, grow")
        add(downButton, "dock south, grow")
    }
}

class BattleButtonPanel: JPanel(MigLayout()){

    class ActionButton(
        text: String
    ) : JButton(text) {
        init {
            font = font.deriveFont(Font.BOLD, 20F)
        }
    }
    val attackButton = ActionButton("Attack")
    val defendButton = ActionButton("Defend")
    val magicatkButton = ActionButton("Magic")
    val specialatkButton = ActionButton("Special")
    init{

        add(attackButton,"dock north, wrap")
        add(defendButton,"dock north, wrap")
        add(magicatkButton, "dock north, wrap")
        add(specialatkButton, "dock north, wrap")
    }
}
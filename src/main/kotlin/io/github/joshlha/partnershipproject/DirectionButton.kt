package io.github.joshlha.partnershipproject

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
    val upButton = ButtonPanel.DirectionButton("↑")
    val downButton = ButtonPanel.DirectionButton("↓")
    val leftButton = ButtonPanel.DirectionButton("←")
    val rightButton = ButtonPanel.DirectionButton("→")
    init{

        add(upButton,"dock north, wrap")
        add(leftButton,"dock west")
        add(rightButton, "dock east, wrap")
        add(downButton, "dock south")
    }
}
package io.github.joshlha.partnershipproject


import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JPanel


class DungeonEditor : JPanel(GridLayout(6,6,5,5)) {

    val button0 = JButton("X0Y0")
    val button1 = JButton("X0Y1")
    val button2 = JButton()
    val button3 = JButton()
    val button4 = JButton()
    val button5 = JButton()
    val button6 = JButton()
    val button7 = JButton()
    val button8 = JButton()
    val button9 = JButton()
    val button10 = JButton()
    val button11 = JButton()
    val button12 = JButton()
    val button13 = JButton()
    val coordButtons = listOf(button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,
        button10,button11,button12,button13)

    init{
        for(button in coordButtons){
            add(button)
        }
    }
}




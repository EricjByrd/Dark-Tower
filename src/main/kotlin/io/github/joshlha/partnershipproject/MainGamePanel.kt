package io.github.joshlha.partnershipproject

import io.github.joshlha.partnershipproject.Main.Companion.backgroundImage
import net.miginfocom.swing.MigLayout
import java.awt.*
import java.awt.SystemColor.text
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.html.HTML.Attribute.N
import javax.swing.text.html.HTML.Tag.S

class MainGamePanel() : JPanel((MigLayout("debug, fill", "[]10[]", "[][][][]"))) {
    val worldview = WorldViewPanel()
    val buttons = ButtonPanel()
    //val allButtons = listOf(upButton, leftButton, rightButton, downButton)


        // Step 2; Add all the components to the layout
        init {
            isOpaque = false
            val forward = ButtonPanel().upButton
            val backwards = ButtonPanel().downButton
            val leftTurn = ButtonPanel().leftButton
            val rightTurn = ButtonPanel().rightButton
            val textPanel = JLabel(playerPosition.coordinates)

            add(worldview, "span, grow, wrap")
            add(textPanel)
            //add(upButton, "flowx")
            //add(leftButton,"flowx")
           // add(rightButton, "flowx")
            add(forward, "skip 1, center, wrap")
            add(leftTurn, " skip 1, sg 1")
            add(backwards, "skip 1")
            add(rightTurn, "sg 1, wrap")
            forward.addActionListener {
                when (playerPosition.d) {
                    'N' -> {
                        playerPosition.y++
                        playerPosition.printPlayerCoords()
                    }

                    'S' -> {
                        playerPosition.y--
                        playerPosition.printPlayerCoords()
                    }

                    'E' -> {
                        playerPosition.x++
                        playerPosition.printPlayerCoords()
                    }

                    'W' -> {
                        playerPosition.x--
                        playerPosition.printPlayerCoords()
                    }

                }
            }
            backwards.addActionListener {
                if(playerPosition.d == 'N'){
                    playerPosition.y--
                    playerPosition.printPlayerCoords()
                }
                if(playerPosition.d == 'S'){
                    playerPosition.y++
                    playerPosition.printPlayerCoords()
                }
                if(playerPosition.d == 'E'){
                    playerPosition.x--
                    playerPosition.printPlayerCoords()
                }
                if(playerPosition.d == 'W'){
                    playerPosition.x++
                    playerPosition.printPlayerCoords()
                }

            }
            leftTurn.addActionListener {
                when(playerPosition.d){
                    'N' -> {playerPosition.d = 'W'
                    playerPosition.printPlayerCoords()}
                    'S' -> {playerPosition.d ='E'
                    playerPosition.printPlayerCoords()}
                    'E' -> {playerPosition.d = 'N'
                        playerPosition.printPlayerCoords()}
                    'W' -> {playerPosition.d = 'S'
                        playerPosition.printPlayerCoords()}
                }

            }
            rightTurn.addActionListener {
                when(playerPosition.d){
                    'N' -> {playerPosition.d = 'E'
                        playerPosition.printPlayerCoords()}
                    'S' -> {playerPosition.d ='W'
                        playerPosition.printPlayerCoords()}
                    'E' -> {playerPosition.d = 'S'
                        playerPosition.printPlayerCoords()}
                    'W' -> {playerPosition.d = 'N'
                        playerPosition.printPlayerCoords()}
                }

            }

        }


//        exitButton.addActionListener(
//            object : ActionListener{
//                override fun actionPerformed(e: ActionEvent?) {
//                    println("Doing something")
//                }
//
//            }
//        )
            //Simplified version of the code above
        }

        // Step 3: Set up event listeners for your components

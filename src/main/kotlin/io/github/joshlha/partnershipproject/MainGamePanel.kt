package io.github.joshlha.partnershipproject

import io.github.joshlha.partnershipproject.Main.Companion.backgroundImage
import net.miginfocom.swing.MigLayout
import java.awt.*
import java.awt.SystemColor.text
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.html.HTML.Attribute.N
import javax.swing.text.html.HTML.Tag.S

class MainGamePanel() : JPanel((MigLayout("debug, fill", "[]10[]", "[][][]"))) {
    val worldview = WorldViewPanel()
    val buttons = ButtonPanel()
    //val allButtons = listOf(upButton, leftButton, rightButton, downButton)


        // Step 2; Add all the components to the layout
        init {
            isOpaque = false
            val forward = buttons.upButton
            val backwards = buttons.downButton
            val leftTurn = buttons.leftButton
            val rightTurn = buttons.rightButton
            val textPanel = JLabel(playerPosition.coordinates)

            add(worldview, "span, grow, wrap")
            add(textPanel)
            //add(upButton, "flowx")
            //add(leftButton,"flowx")
           // add(rightButton, "flowx")
            add(buttons)
            forward.addActionListener {
                when (playerPosition.d) {
                    playerPosition.Direction.N -> {
                        playerPosition.y++
                        playerPosition.printPlayerCoords()
                    }

                    playerPosition.Direction.S -> {
                        playerPosition.y--
                        playerPosition.printPlayerCoords()
                    }

                    playerPosition.Direction.E -> {
                        playerPosition.x++
                        playerPosition.printPlayerCoords()
                    }

                    playerPosition.Direction.W -> {
                        playerPosition.x--
                        playerPosition.printPlayerCoords()
                    }

                }
            }
            backwards.addActionListener {
                if(playerPosition.d == playerPosition.Direction.N){
                    playerPosition.y--
                    playerPosition.printPlayerCoords()
                }
                if(playerPosition.d == playerPosition.Direction.S){
                    playerPosition.y++
                    playerPosition.printPlayerCoords()
                }
                if(playerPosition.d == playerPosition.Direction.E){
                    playerPosition.x--
                    playerPosition.printPlayerCoords()
                }
                if(playerPosition.d == playerPosition.Direction.W){
                    playerPosition.x++
                    playerPosition.printPlayerCoords()
                }

            }
            leftTurn.addActionListener {
                when(playerPosition.d){
                    playerPosition.Direction.N -> {playerPosition.d = playerPosition.Direction.W
                    playerPosition.printPlayerCoords()}
                    playerPosition.Direction.S -> {playerPosition.d =playerPosition.Direction.E
                    playerPosition.printPlayerCoords()}
                    playerPosition.Direction.E -> {playerPosition.d = playerPosition.Direction.N
                        playerPosition.printPlayerCoords()}
                    playerPosition.Direction.W -> {playerPosition.d = playerPosition.Direction.S
                        playerPosition.printPlayerCoords()}
                }

            }
            rightTurn.addActionListener {
                when(playerPosition.d){
                    playerPosition.Direction.N -> {playerPosition.d = playerPosition.Direction.E
                        playerPosition.printPlayerCoords()}
                    playerPosition.Direction.S -> {playerPosition.d =playerPosition.Direction.W
                        playerPosition.printPlayerCoords()}
                    playerPosition.Direction.E -> {playerPosition.d = playerPosition.Direction.S
                        playerPosition.printPlayerCoords()}
                    playerPosition.Direction.W -> {playerPosition.d = playerPosition.Direction.N
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

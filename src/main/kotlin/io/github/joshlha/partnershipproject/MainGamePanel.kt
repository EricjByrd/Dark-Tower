package io.github.joshlha.partnershipproject

import io.github.joshlha.partnershipproject.Main.Companion.backgroundImage
import io.github.joshlha.partnershipproject.playerPosition.d
import net.miginfocom.swing.MigLayout
import java.awt.*
import java.awt.Event.F1
import java.awt.SystemColor.text
import java.io.File
import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.swing.*
import javax.swing.text.html.HTML.Attribute.N
import javax.swing.text.html.HTML.Tag.S

class MainGamePanel() : JPanel((MigLayout("debug, fill", "[]10[]", "[][][]"))) {
    val worldview = WorldViewLabel()
    val buttons = ButtonPanel()
    var nextLocation = playerPosition.returnUsableCoords()

    //val allButtons = listOf(upButton, leftButton, rightButton, downButton)


        // Step 2; Add all the components to the layout
        init {
            isOpaque = false
            val forward = buttons.upButton
            val backwards = buttons.downButton
            val leftTurn = buttons.leftButton
            val rightTurn = buttons.rightButton
            val textPanel = JLabel(playerPosition.coordinates)

            add(worldview, "span 3")//span, grow, wrap")
            //add(upButton, "flowx")
            //add(leftButton,"flowx")
           // add(rightButton, "flowx")
            add(buttons)
            forward.addActionListener {
                when (playerPosition.d) {
                    playerPosition.Direction.N -> {
                        playerPosition.y++
                        playerPosition.printPlayerCoords()
                        worldview.setIcon(ImageIcon("src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"))
                        println("src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png")
                        worldview.revalidate()
                        worldview.repaint()
                            }



                    playerPosition.Direction.S -> {
                        if(playerPosition.y == 0){println("cannot continue")}
                        else{
                        playerPosition.y--
                        playerPosition.printPlayerCoords()}
                    }

                    playerPosition.Direction.E -> {
                        playerPosition.x++
                        playerPosition.printPlayerCoords()
                    }

                    playerPosition.Direction.W -> {
                        if(playerPosition.x == 0){println("cannot continue")}
                        else {playerPosition.x--
                        playerPosition.printPlayerCoords()}
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

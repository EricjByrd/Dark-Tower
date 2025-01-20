package io.github.ericjbyrd.partnershipproject

import net.miginfocom.swing.MigLayout
import java.awt.*
import java.io.File
import javax.swing.*

class DeprecatedMainGamePanel() : JPanel((MigLayout("debug, wrap 2", "[][][]","[][][]"))) {
    //Step 1; Initialize all components
    val totalview = JPanel()
    val worldview = JLabel()
    val buttons = ButtonPanel()
    val playerStatsPanel = PlayerStatusPanel()


    // Step 2; Add all the components to the layout (frame in this case)
    init {
        isOpaque = false
        val forward = buttons.upButton
        val backwards = buttons.downButton
        val leftTurn = buttons.leftButton
        val rightTurn = buttons.rightButton

        totalview.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5))
        add(totalview, "grow")
        totalview.add(worldview)
        playerStatsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5))
        add(playerStatsPanel)
        add(buttons,"skip 2")
        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
        fun updateLabelCoords(){
            playerStatsPanel.playerCoord.setText(playerPosition.getPlayerCoords())
        }
        //why won't path work in my ActionListener?
        worldview.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5))
        worldview.setIcon(ImageIcon(path))


        forward.addActionListener {
            val path =  "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y +1}D${playerPosition.d}F${playerPosition.f}.png"
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    playerPosition.printPlayerCoords()
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.y++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }


                playerPosition.Direction.S -> {
                    val path =  "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y -1}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.y--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                        worldview
                    }

                }

                playerPosition.Direction.E -> {
                    val path =  "src/main/resources/maps/F1/X${playerPosition.x +1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                        Encounter(playerPosition.x,playerPosition.y,)
                        }
                }

                playerPosition.Direction.W -> {
                    val path =  "src/main/resources/maps/F1/X${playerPosition.x -1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()

                    }
                }

            }
        }

        backwards.addActionListener {
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y - 1}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.y--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.S -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y + 1}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.y++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.E -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x - 1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.W -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x + 1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

            }
        }
        leftTurn.addActionListener {
                when (playerPosition.d) {
                    playerPosition.Direction.N -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DWF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.W
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    playerPosition.Direction.S -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DEF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.E
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    playerPosition.Direction.E -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DNF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.N
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    playerPosition.Direction.W -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DSF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.S
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }
                }

            }
            rightTurn.addActionListener {
                when (playerPosition.d) {
                    playerPosition.Direction.N -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DEF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.E
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    playerPosition.Direction.S -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DWF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.W
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    playerPosition.Direction.E -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DSF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.S
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    playerPosition.Direction.W -> {
                        val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DNF${playerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            playerPosition.d = playerPosition.Direction.N
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }
                }

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


        // Step 3: Set up event listeners for your components

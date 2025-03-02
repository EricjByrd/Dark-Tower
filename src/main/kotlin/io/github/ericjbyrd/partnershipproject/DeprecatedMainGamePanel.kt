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
        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}D${PlayerPosition.d}F${PlayerPosition.f}.png"
        fun updateLabelCoords(){
            playerStatsPanel.playerCoord.setText(PlayerPosition.getPlayerCoords())
        }
        //why won't path work in my ActionListener?
        worldview.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5))
        worldview.setIcon(ImageIcon(path))


        forward.addActionListener {
            val path =  "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y +1}D${PlayerPosition.d}F${PlayerPosition.f}.png"
            when (PlayerPosition.d) {
                PlayerPosition.Direction.N -> {
                    PlayerPosition.printPlayerCoords()
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.y++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }


                PlayerPosition.Direction.S -> {
                    val path =  "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y -1}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.y--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                        worldview
                    }

                }

                PlayerPosition.Direction.E -> {
                    val path =  "src/main/resources/maps/F1/X${PlayerPosition.x +1}Y${PlayerPosition.y}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.x++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                        }
                }

                PlayerPosition.Direction.W -> {
                    val path =  "src/main/resources/maps/F1/X${PlayerPosition.x -1}Y${PlayerPosition.y}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.x--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()

                    }
                }

            }
        }

        backwards.addActionListener {
            when (PlayerPosition.d) {
                PlayerPosition.Direction.N -> {
                    val path =
                        "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y - 1}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.y--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                PlayerPosition.Direction.S -> {
                    val path =
                        "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y + 1}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.y++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                PlayerPosition.Direction.E -> {
                    val path =
                        "src/main/resources/maps/F1/X${PlayerPosition.x - 1}Y${PlayerPosition.y}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.x--
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                PlayerPosition.Direction.W -> {
                    val path =
                        "src/main/resources/maps/F1/X${PlayerPosition.x + 1}Y${PlayerPosition.y}D${PlayerPosition.d}F${PlayerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        PlayerPosition.x++
                        worldview.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

            }
        }
        leftTurn.addActionListener {
                when (PlayerPosition.d) {
                    PlayerPosition.Direction.N -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DWF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.W
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    PlayerPosition.Direction.S -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DEF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.E
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    PlayerPosition.Direction.E -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DNF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.N
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    PlayerPosition.Direction.W -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DSF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.S
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }
                }

            }
            rightTurn.addActionListener {
                when (PlayerPosition.d) {
                    PlayerPosition.Direction.N -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DEF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.E
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    PlayerPosition.Direction.S -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DWF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.W
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    PlayerPosition.Direction.E -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DSF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.S
                            worldview.setIcon(ImageIcon(path))
                            updateLabelCoords()
                        }
                    }

                    PlayerPosition.Direction.W -> {
                        val path = "src/main/resources/maps/F1/X${PlayerPosition.x}Y${PlayerPosition.y}DNF${PlayerPosition.f}.png"
                        val file = File(path)
                        if (file.exists()) {
                            PlayerPosition.d = PlayerPosition.Direction.N
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

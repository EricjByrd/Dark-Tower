package io.github.ericjbyrd.partnershipproject


import java.awt.Color
import java.io.File
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JLayeredPane
import javax.swing.JPanel


class MainGamePanel : JLayeredPane() {
    val fourthLayerPanel = PlayerStatusPanel()
    val thirdLayerPanel = JPanel()
    val secondLayerPanel = JPanel()
    val firstLayerPanel = JPanel()
    val worldView = JLabel()
    val buttonsPanel = ButtonPanel()
    val playerStatsPanel = PlayerStatusPanel()

    init{
        val monsterLabel = JLabel()
        val monsterImage = "src/main/resources/monsters/demon.png"
        val dialogueLabel = JLabel("Let's get to work.")
        val forward = buttonsPanel.upButton
        val backwards = buttonsPanel.downButton
        val leftTurn = buttonsPanel.leftButton
        val rightTurn = buttonsPanel.rightButton
        val SHADED = Color(0,0,0,200)
        val NORMAL = Color(0,0,0,0)

        //adding button panel to the JLayered Pane
        add(buttonsPanel)
        buttonsPanel.setBounds(700,400,100,67)

        //Player Stats Panel
        add(fourthLayerPanel)
        setLayer(fourthLayerPanel, 0,0)
        fourthLayerPanel.setOpaque(true)
        fourthLayerPanel.setBackground(Color.BLACK)
        fourthLayerPanel.setBounds( 550,30,150, 300)
        fourthLayerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true))


        add(thirdLayerPanel,1,1)
        thirdLayerPanel.setOpaque(true)
        thirdLayerPanel.setBackground(Color.BLACK)
        thirdLayerPanel.setBounds( 25,400,600, 150)
        thirdLayerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE,5,true))

        thirdLayerPanel.add(dialogueLabel)


        //monsterLayer
        add(monsterLabel,0)
        monsterLabel.setIcon(ImageIcon(monsterImage))
        monsterLabel.setBounds(215,160,300,300)
        monsterLabel.setVisible(true)

        //encounterLayer
        add(secondLayerPanel, 1)
        secondLayerPanel.setOpaque(false)
        secondLayerPanel.setBounds(25,26,600,400)
        //secondLayerPanel.setBackground(NORMAL)
        secondLayerPanel.setBackground(SHADED)
                //when enemy is encountered, setBackground(SHADED), setOpaque(true)
                //and set monsterLabel visible + ImageIcon(monsterImage)


        add(firstLayerPanel)
        firstLayerPanel.setOpaque(true)
        firstLayerPanel.setBackground(NORMAL)
        firstLayerPanel.setBounds(25,25,600,400)
        firstLayerPanel.add(worldView)

        worldView.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true))
        val path =  "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y }D${playerPosition.d}F${playerPosition.f}.png"
        worldView.setIcon(ImageIcon(path))

        fun updateLabelCoords(){
            fourthLayerPanel.playerCoord.setText(playerPosition.getPlayerCoords())
        }
        fun outOfBoundsText(){
            dialogueLabel.setText("Ouch!")
        }
        fun clearText(){
            dialogueLabel.setText("")
        }
        forward.addActionListener {
            val path =  "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y +1}D${playerPosition.d}F${playerPosition.f}.png"
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    playerPosition.printPlayerCoords()
                    val file = File(path)
                    if (file.exists()) {
                        clearText()
                        playerPosition.y++
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                    else{
                        outOfBoundsText()
                    }
                }


                playerPosition.Direction.S -> {
                    val path =  "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y -1}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.y--
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                        worldView
                    }

                }

                playerPosition.Direction.E -> {
                    val path =  "src/main/resources/maps/F1/X${playerPosition.x +1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x++
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                        Encounter(playerPosition.x,playerPosition.y,)
                    }
                }

                playerPosition.Direction.W -> {
                    val path =  "src/main/resources/maps/F1/X${playerPosition.x -1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x--
                        worldView.setIcon(ImageIcon(path))
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
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.S -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y + 1}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.y++
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.E -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x - 1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x--
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.W -> {
                    val path =
                        "src/main/resources/maps/F1/X${playerPosition.x + 1}Y${playerPosition.y}D${playerPosition.d}F${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.x++
                        worldView.setIcon(ImageIcon(path))
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
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.S -> {
                    val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DEF${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.d = playerPosition.Direction.E
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.E -> {
                    val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DNF${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.d = playerPosition.Direction.N
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.W -> {
                    val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DSF${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.d = playerPosition.Direction.S
                        worldView.setIcon(ImageIcon(path))
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
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.S -> {
                    val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DWF${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.d = playerPosition.Direction.W
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.E -> {
                    val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DSF${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.d = playerPosition.Direction.S
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }

                playerPosition.Direction.W -> {
                    val path = "src/main/resources/maps/F1/X${playerPosition.x}Y${playerPosition.y}DNF${playerPosition.f}.png"
                    val file = File(path)
                    if (file.exists()) {
                        playerPosition.d = playerPosition.Direction.N
                        worldView.setIcon(ImageIcon(path))
                        updateLabelCoords()
                    }
                }
            }

        }
    }
}






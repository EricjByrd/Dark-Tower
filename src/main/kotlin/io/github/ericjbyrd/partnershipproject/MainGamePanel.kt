package io.github.ericjbyrd.partnershipproject


import io.github.ericjbyrd.partnershipproject.playerPosition.stepCounter
import io.github.ericjbyrd.partnershipproject.playerPosition.x
import io.github.ericjbyrd.partnershipproject.playerPosition.y
import java.awt.Color
import java.io.File
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JLayeredPane
import javax.swing.JPanel
import kotlin.math.E
import kotlin.properties.Delegates


class MainGamePanel : JLayeredPane() {
    val fourthLayerPanel = PlayerStatusPanel()
    val thirdLayerPanel = JPanel()
    val secondLayerPanel = JPanel()
    val firstLayerPanel = JPanel()
    val worldView = JLabel()
    val buttonsPanel = ButtonPanel()
    val playerStatsPanel = PlayerStatusPanel()

    init {
        val monsterLabel = JLabel()
        val monsterImage = "src/main/resources/monsters/demon.png"
        val dialogueLabel = JLabel("Let's get to work.")
        val forward = buttonsPanel.upButton
        val backwards = buttonsPanel.downButton
        val leftTurn = buttonsPanel.leftButton
        val rightTurn = buttonsPanel.rightButton
        val SHADED = Color(0, 0, 0, 200)
        val NORMAL = Color(0, 0, 0, 0)

        //adding button panel to the JLayered Pane
        add(buttonsPanel)
        buttonsPanel.setBounds(700, 400, 100, 67)

        //Player Stats Panel
        add(fourthLayerPanel)
        setLayer(fourthLayerPanel, 0, 0)
        fourthLayerPanel.setOpaque(true)
        fourthLayerPanel.setBackground(Color.BLACK)
        fourthLayerPanel.setBounds(550, 30, 150, 300)
        fourthLayerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true))


        add(thirdLayerPanel, 1, 1)
        thirdLayerPanel.setOpaque(true)
        thirdLayerPanel.setBackground(Color.BLACK)
        thirdLayerPanel.setBounds(25, 400, 600, 150)
        thirdLayerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true))

        thirdLayerPanel.add(dialogueLabel)


        //monsterLayer
        add(monsterLabel, 0)
        monsterLabel.setIcon(ImageIcon(monsterImage))
        monsterLabel.setBounds(215, 160, 300, 300)
        monsterLabel.setVisible(true)

        //encounterLayer
        add(secondLayerPanel, 1)
        secondLayerPanel.setOpaque(false)
        secondLayerPanel.setBounds(25, 26, 600, 400)
        //secondLayerPanel.setBackground(NORMAL)
        secondLayerPanel.setBackground(SHADED)
        //when enemy is encountered, setBackground(SHADED), setOpaque(true)
        //and set monsterLabel visible + ImageIcon(monsterImage)


        add(firstLayerPanel)
        firstLayerPanel.setOpaque(true)
        firstLayerPanel.setBackground(NORMAL)
        firstLayerPanel.setBounds(25, 25, 600, 400)
        firstLayerPanel.add(worldView)

        worldView.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, true))
        val levelOne = buildLevel()
        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.northImagePath))


        fun updateHUD() {
            fourthLayerPanel.stepCounter.setText(stepCounter())
            fourthLayerPanel.playerCoord.setText(playerPosition.getPlayerCoords())
        }

        forward.addActionListener {
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    playerPosition.printPlayerCoords()
                    val viewCheck = levelOne[playerPosition.x][playerPosition.y + 1]?.northImage
                    if (viewCheck == null)
                    {
                        println("Ouch!")
                    } else {
                        playerPosition.y++
                        updateHUD()
                        println(playerPosition.getPlayerCoords())
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.northImagePath))
                    }
                }

                playerPosition.Direction.S -> {
                    val viewCheck = levelOne[playerPosition.x][playerPosition.y-1]?.southImagePath
                    playerPosition.printPlayerCoords()
                    if (viewCheck == null) {
                        println("Ouch!")
                    } else {
                        playerPosition.y--
                        println(playerPosition.getPlayerCoords())
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.southImagePath))
                    }
                }

                playerPosition.Direction.E -> {
                    playerPosition.printPlayerCoords()
                    if (levelOne[playerPosition.x + 1][playerPosition.y]?.eastImage == null) {
                        println("Ouch!")
                    } else {
                        playerPosition.x++
                        println(playerPosition.getPlayerCoords())
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.eastImagePath))
                    }
                }

                playerPosition.Direction.W -> {
                    playerPosition.printPlayerCoords()
                    if (levelOne[playerPosition.x - 1][playerPosition.y]?.westImage == null) {
                        println("Ouch!")
                    } else {
                        println(playerPosition.getPlayerCoords())
                        playerPosition.x--
                        println(worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.westImagePath)))
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.westImagePath))
                    }
                }
            }
        }
        backwards.addActionListener{
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    playerPosition.printPlayerCoords()
                    if (levelOne[playerPosition.x][playerPosition.y - 1]?.northImage == null) {
                        println("Ouch!")
                    } else {
                        playerPosition.y--
                        println(playerPosition.getPlayerCoords())
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.northImagePath))
                    }
                }

                playerPosition.Direction.S -> {
                    playerPosition.printPlayerCoords()
                    if (levelOne[playerPosition.x][playerPosition.y - 1]?.southImage == null) {
                        println("Ouch!")
                    } else {
                        playerPosition.y--
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.southImagePath))
                    }
                }

                playerPosition.Direction.E -> {
                    playerPosition.printPlayerCoords()
                    if (levelOne[playerPosition.x + 1][playerPosition.y]?.eastImage == null) {
                        println("Ouch!")
                    } else {
                        playerPosition.x++
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.eastImagePath))
                    }
                }

                playerPosition.Direction.W -> {
                    playerPosition.printPlayerCoords()
                    if (levelOne[playerPosition.x - 1][playerPosition.y]?.westImage == null) {
                        println("Ouch!")
                    } else {
                        playerPosition.x--
                        worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.westImagePath))
                    }
                }
            }
        }
        leftTurn.addActionListener{
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    playerPosition.d = playerPosition.Direction.W
                    println(playerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.westImagePath))
                    }
                playerPosition.Direction.S -> {
                    playerPosition.d = playerPosition.Direction.E
                    playerPosition.printPlayerCoords()
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.eastImagePath))
                }
                playerPosition.Direction.E -> {
                    playerPosition.d = playerPosition.Direction.N
                    println(playerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.northImagePath))
                }
                playerPosition.Direction.W -> {
                    playerPosition.d = playerPosition.Direction.S
                    println(playerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.southImagePath))
                }
            }
        }
        rightTurn.addActionListener{
            when (playerPosition.d) {
                playerPosition.Direction.N -> {
                    playerPosition.d = playerPosition.Direction.E
                    println(playerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.eastImagePath))
                }
                playerPosition.Direction.S -> {
                    playerPosition.d = playerPosition.Direction.W
                    playerPosition.printPlayerCoords()
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.westImagePath))
                }
                playerPosition.Direction.E -> {
                    playerPosition.d = playerPosition.Direction.S
                    println(playerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.southImagePath))
                }
                playerPosition.Direction.W -> {
                    playerPosition.d = playerPosition.Direction.N
                    println(playerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(levelOne[playerPosition.x][playerPosition.y]?.northImagePath))
                }
            }
        }
    }
}
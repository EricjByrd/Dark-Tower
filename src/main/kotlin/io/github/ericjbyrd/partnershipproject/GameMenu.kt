package io.github.ericjbyrd.partnershipproject

import Encounter
import net.miginfocom.swing.MigLayout
import java.awt.FlowLayout
//import sun.jvm.hotspot.oops.CellTypeState.value
import java.awt.Font
import java.awt.Toolkit
import javax.swing.*
import javax.swing.text.StyleConstants.setIcon

class GameMenu : JPanel(MigLayout("flowy, alignx center, aligny center, gap 50")) {

    // Step 1: Initialize all data and components
    val newGameButton = GameMenuButton("New Game", "Start a new game!")
    val loadButton = GameMenuButton("Load Game", "Load your game.")
    val optionsButton = GameMenuButton("Options", "Change settings")
    val dungeonEditor = GameMenuButton("Dungeon Editor", "Create your own Dungeon")
    val exitButton = GameMenuButton("Exit", "Exit Game")
    val screenRes = Toolkit.getDefaultToolkit().screenSize
    val fullscrnWidth = screenRes.getWidth()
    val fullscrnHeight = screenRes.getHeight()

    val allButtons = listOf(newGameButton, loadButton, dungeonEditor ,optionsButton, exitButton)

    // Step 2; Add all the components to the layout
    init {
        isOpaque = false
        for (button in allButtons) {
            add(button, "sg" )
        }
//        exitButton.addActionListener(
//            object : ActionListener{
//                override fun actionPerformed(e: ActionEvent?) {
//                    println("Doing something")
//                }
//
//            }
//        )
        //Simplified version of the code above:
        newGameButton.addActionListener {
            JFrame("Record of Lodoss Wars").apply {
                contentPane = MainGamePanel()
                setSize(fullscrnWidth.toInt(), fullscrnHeight.toInt())
                setLocationRelativeTo(null)
                isVisible = true
                defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE

            }
        }
        loadButton.addActionListener {
            JFrame("Load Game").apply {
                val levelOne = buildLevel()
                println(levelOne[0][0]?.northImagePath)
                contentPane = Encounter()
                setSize(600, 400)
                setLocationRelativeTo(null)
                isVisible = true
                defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE

            }
        }
        dungeonEditor.addActionListener{
            JFrame("Dungeon Editor").apply {
                contentPane = JPanel()
                setSize(fullscrnWidth.toInt(), fullscrnHeight.toInt())
                setLocationRelativeTo(null)
                isVisible = true
                defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            }
        }
        optionsButton.addActionListener {
            JFrame("Options").apply {
                contentPane = JPanel()
                setSize(600, 400)
                setLocationRelativeTo(null)
                isVisible = true
                defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            }
        }
        exitButton.addActionListener {
            val mainframe = SwingUtilities.getWindowAncestor(exitButton)
            val results = JOptionPane.showConfirmDialog(mainframe, "Are you sure you want to quit?")
            if(results == JOptionPane.YES_OPTION) mainframe.dispose()
        }
    }

    class GameMenuButton(
        text: String,
        val description: String,

    ) : JButton(text){
        init {
            font = font.deriveFont(Font.BOLD, 20F)
        }
    }
    // Step 3: Set up event listeners for your components

}


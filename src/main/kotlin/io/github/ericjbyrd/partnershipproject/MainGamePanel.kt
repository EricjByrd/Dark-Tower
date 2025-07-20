package io.github.ericjbyrd.partnershipproject
import io.github.ericjbyrd.partnershipproject.PlayerPosition.counter
import io.github.ericjbyrd.partnershipproject.PlayerPosition.stepCounter
import io.github.ericjbyrd.partnershipproject.monsterRepository.goblin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.JLayeredPane
import javax.swing.JPanel
import kotlinx.coroutines.*
import java.awt.KeyEventDispatcher
import java.awt.Transparency
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.Action
import javax.swing.JButton
import kotlin.coroutines.resume


class MainGamePanel : JLayeredPane() {
    val player = playerCharacter(name = "Eric", health = 60, atkPoints = 50, defPoints = 40,
    matkPoints = 30, mdefPoints =  10, mp = 50, maxMP = 50, maxHealth = 60)
    val fifthLayerPanel = MonsterStatusPanel(goblin)
    val fourthLayerPanel = PlayerStatusPanel(player)
    val thirdLayerPanel = JPanel()
    val secondLayerPanel = JPanel()
    val firstLayerPanel = JPanel()
    val worldView = JLabel()
    val buttonsPanel = ButtonPanel()
    val actionButtonPanel = BattleButtonPanel()
    var battleMode: Boolean = false
    var playerTurn: Boolean = false

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
        add(actionButtonPanel)
        buttonsPanel.setBounds(700, 400, 200, 200)
        actionButtonPanel.setBounds(700, 400, 200, 200)
        actionButtonPanel.isVisible= false

        //MonsterStatusPanel
        add(fifthLayerPanel)
        setLayer(fifthLayerPanel, 1, 0)
        fifthLayerPanel.setOpaque(true)
        fifthLayerPanel.setBackground(Color.BLACK)
        fifthLayerPanel.setBounds(0, 30, 150, 150)
        fifthLayerPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5, true))
        fifthLayerPanel.isVisible = false

        //Player Stats Panel
        add(fourthLayerPanel)
        setLayer(fourthLayerPanel, 1, 0)
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


        //monsterLabelLayer
        add(monsterLabel, 0)
        monsterLabel.setIcon(ImageIcon(monsterImage))
        monsterLabel.setBounds(215, 160, 300, 300)
        monsterLabel.setVisible(false)

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
        val levelOne = buildLevel(levelX = 20, levelY = 20, f = 1)
        val levelTwo = buildLevel(levelX = 40, levelY = 40, f = 2)
        val currentLevel = levelOne
        val currentLevelMaxX = currentLevel.size-1
        val currentLevelMaxY = currentLevel[0].size-1
        worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.northImagePath))

        class DialogueControl(){
            fun setDialogue(string: String){
                dialogueLabel.setText(string)
            }
        }

        class ButtonAccess {
            fun toggleActionButtons(panel: JPanel, status: Boolean) {
                for (button in actionButtonPanel.components) {
                    if (button is JButton) {
                        button.isEnabled = status
                    }
                }

            }
        }

        class Encounter{
            //suspend function that takes a JButton and returns a String with suspendCancellableCoroutine
            suspend fun awaitAction(button: JButton): String = suspendCancellableCoroutine {
                cont ->
                lateinit var listener: ActionListener
                listener = ActionListener { e ->
                    cont.resume(e.actionCommand)
                    button.removeActionListener(listener)
                }
                button.addActionListener(listener)
                cont.invokeOnCancellation{
                    button.removeActionListener(listener)
                }
            }

            private val uiScope = CoroutineScope(Dispatchers.Main)

            fun battleEngage(player: playerCharacter, monster: Monster){
                battleMode = true
                uiScope.launch {
                    secondLayerPanel.setOpaque(true)
                    secondLayerPanel.setBackground(SHADED)
                    secondLayerPanel.isVisible = true
                    secondLayerPanel.repaint()
                    fifthLayerPanel.isVisible = true
                    buttonsPanel.isVisible = false
                    actionButtonPanel.isVisible = true
                    dialogueLabel.setText(monster.greet())
                    actionButtonPanel.attackButton.addActionListener{
                        dialogueLabel.setText(player.attack(monster))
                        monster.health = (monster.health-4)
                        fifthLayerPanel.health.setText("Health: "+ monster.health.toString())
                        dialogueLabel.setText("${monster.name} took 5 damage")
                        playerTurn = false
                    }
                ButtonAccess().toggleActionButtons(actionButtonPanel,false)
                withContext(Dispatchers.Default){
                monsterLabel.isVisible = true
                monsterLabel.setBackground(Color.RED)
                var x = 0
                delay(1000)
                while (monster.health >= 0) {
                    dialogueLabel.setText("${player.name}'s turn")
                    playerTurn = true
                    ButtonAccess().toggleActionButtons(actionButtonPanel,true)
                    awaitAction(actionButtonPanel.attackButton)
                    ButtonAccess().toggleActionButtons(actionButtonPanel,false)
                    delay(1000)
                    dialogueLabel.setText(monster.attack(player))
                    if (monster.health > 0) {
                    player.health = (player.health - 1)
                    fourthLayerPanel.health.setText("Health: "+ player.health.toString())
                    dialogueLabel.setText("${player.name} took 5 damage")
                    delay(1000)
                    println(x)
                    x = x + 1
                        dialogueLabel.setText("$player.name's turn")
                    }
                    else{
                        break
                    }
                }
                if (player.health <= 0)
                {
                    dialogueLabel.setText( "${player.name} has fallen")
                }
                if(monster.health <= 0)
                {
                    dialogueLabel.setText("${monster.name} has been defeated!")
                    secondLayerPanel.setOpaque(false)
                    secondLayerPanel.repaint()
                    monsterLabel.setVisible(false)
                    buttonsPanel.setVisible(true)
                    battleMode = false
                    delay(2000)
                    dialogueLabel.setText("")
                    fifthLayerPanel.isVisible = false
                    monster.health = monster.maxHealth
                    fifthLayerPanel.health.setText("Health: "+ monster.health.toString())
                }
                    }
                }

            }
            fun triggerEncounter(){
                if (counter ==5)
                {
                    Encounter().battleEngage(player, goblin)
                    counter = 0

                }
            }
        }


        fun updateHUD() {
            fourthLayerPanel.stepCounter.setText(stepCounter())
            fourthLayerPanel.playerCoord.setText(PlayerPosition.getPlayerCoords())
            Encounter().triggerEncounter()
            fourthLayerPanel.mp.setText("MP: "+ player.mp.toString())
            fourthLayerPanel.health.setText("Health: "+ player.health.toString())

        }
        
        forward.addActionListener {
            when (PlayerPosition.d) {
                PlayerPosition.Direction.N -> {
                    PlayerPosition.printPlayerCoords()
                    if (currentLevel[PlayerPosition.x][PlayerPosition.y+1] != null){
                        PlayerPosition.incrementY(0,currentLevelMaxY)
                        println(PlayerPosition.getPlayerCoords())
                        updateHUD()
                        worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.northImagePath))
                    }
                    else {

                    }
                }

                PlayerPosition.Direction.S -> {
                    PlayerPosition.printPlayerCoords()
                    if (PlayerPosition.y in 1..currentLevelMaxY && currentLevel[PlayerPosition.x][PlayerPosition.y-1] != null){
                        PlayerPosition.printPlayerCoords()
                        PlayerPosition.decrementY(0,currentLevelMaxY)
                        updateHUD()
                        println(PlayerPosition.getPlayerCoords())
                        worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.southImagePath))
                    }
                    else {
                        print("Ouch!")
                    }
                }

                PlayerPosition.Direction.E -> {
                    if (PlayerPosition.x in 0..currentLevelMaxX && currentLevel[PlayerPosition.x+1][PlayerPosition.y] != null){
                        PlayerPosition.printPlayerCoords()
                        PlayerPosition.incrementX(0,currentLevelMaxX)
                        updateHUD()
                        println(PlayerPosition.getPlayerCoords())
                        worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.eastImagePath))
                    }
                    else{
                        print("Ouch")
                    }
                }

                PlayerPosition.Direction.W ->
                    if (PlayerPosition.x in 0..currentLevelMaxX && currentLevel[PlayerPosition.x-1][PlayerPosition.y] != null){
                    PlayerPosition.printPlayerCoords()
                    PlayerPosition.decrementX(0,currentLevelMaxX)
                    updateHUD()
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.westImagePath))
                }
                else{
                    print("Ouch")
                }
            }
        }

        backwards.addActionListener{
            when (PlayerPosition.d) {
                PlayerPosition.Direction.N -> {
                    PlayerPosition.printPlayerCoords()
                    PlayerPosition.decrementY(0,currentLevelMaxY)
                    updateHUD()
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.northImagePath))
                }

                PlayerPosition.Direction.S -> {
                    PlayerPosition.printPlayerCoords()
                    PlayerPosition.incrementY(0,currentLevelMaxY)
                    updateHUD()
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.southImagePath))
                }

                PlayerPosition.Direction.E -> {
                    PlayerPosition.printPlayerCoords()
                    PlayerPosition.decrementX(0,currentLevelMaxX)
                    updateHUD()
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.eastImagePath))
                }

                PlayerPosition.Direction.W -> {
                    PlayerPosition.printPlayerCoords()
                    PlayerPosition.incrementX(0,currentLevelMaxX)
                    updateHUD()
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.westImagePath))
                }
            }
        }
        leftTurn.addActionListener{
            when (PlayerPosition.d) {
                PlayerPosition.Direction.N -> {
                    PlayerPosition.d = PlayerPosition.Direction.W
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.westImagePath))
                    }
                PlayerPosition.Direction.S -> {
                    PlayerPosition.d = PlayerPosition.Direction.E
                    PlayerPosition.printPlayerCoords()
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.eastImagePath))
                }
                PlayerPosition.Direction.E -> {
                    PlayerPosition.d = PlayerPosition.Direction.N
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.northImagePath))
                }
                PlayerPosition.Direction.W -> {
                    PlayerPosition.d = PlayerPosition.Direction.S
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.southImagePath))
                }
            }
        }
        rightTurn.addActionListener{
            when (PlayerPosition.d) {
                PlayerPosition.Direction.N -> {
                    PlayerPosition.d = PlayerPosition.Direction.E
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.eastImagePath))
                }
                PlayerPosition.Direction.S -> {
                    PlayerPosition.d = PlayerPosition.Direction.W
                    PlayerPosition.printPlayerCoords()
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.westImagePath))
                }
                PlayerPosition.Direction.E -> {
                    PlayerPosition.d = PlayerPosition.Direction.S
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.southImagePath))
                }
                PlayerPosition.Direction.W -> {
                    PlayerPosition.d = PlayerPosition.Direction.N
                    println(PlayerPosition.getPlayerCoords())
                    worldView.setIcon(ImageIcon(currentLevel[PlayerPosition.x][PlayerPosition.y]?.northImagePath))
                }
            }
        }
    }
}
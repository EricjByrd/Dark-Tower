package io.github.ericjbyrd.partnershipproject

import net.miginfocom.swing.MigLayout
import org.jdesktop.swingx.JXDialog
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JPanel
import java.util.HashMap
//player X and Y change
//if player X and Y == enemy X and Y
//function (enemyObject.enemyName){
//JLabel(enemy name + " has appeared!") appeared
// run combatPanel
//}

fun Encounter(pX:Int, pY:Int){
    val item = JFrame()
    //actually, how about I set up a list of encounter points in the game
    // encounter x and encounter y
    // if player x == an encounter x AND "player y == an encounter y
    // execute encounter stuff?
    //This OR a step calculator that resets after every encounter.
    //but then I'd have to discard the x y parameters for my Character class and all it's subclasses.
    if ((pX == 1) and (pY == 2))
        //getMonster(monsterX, monsterY)

        println("MONSTER")
            MainGamePanel().add(JDialog(item))
}
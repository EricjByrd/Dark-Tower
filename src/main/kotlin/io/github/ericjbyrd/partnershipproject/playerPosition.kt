package io.github.ericjbyrd.partnershipproject
import io.github.ericjbyrd.partnershipproject.PlayerPosition.f
import io.github.ericjbyrd.partnershipproject.PlayerPosition.x
import io.github.ericjbyrd.partnershipproject.monsterRepository.goblin
import net.miginfocom.layout.LinkHandler.X
import java.awt.Color
import java.awt.Panel
import java.io.File
import javax.swing.ImageIcon
import javax.swing.JPanel

object PlayerPosition {
    //this can serve as coordinates AND locations for the map.
    //Idea credited to the brilliant RyiSnow!
    var counter = 0
    var x: Int = 0
    var y: Int = 0
    var d: Direction = Direction.N

    enum class Direction{
        N,S,E,W
    }
    var f: Int = 1
    //I decided to include dynamic coordinates for easier programming.
    val coordinates= "X:${x},Y:${y},D:${d}F${f}"
    fun incrementX(minX: Int, maxX: Int){
        x = x.inc().coerceIn(minX..maxX)
    }
    fun decrementX(minX: Int, maxX: Int){
        x = x.dec().coerceIn(minX..maxX)
    }
    fun incrementY(minY: Int, maxY: Int){
        y = y.inc().coerceIn(minY..maxY)
    }
    fun decrementY(minY: Int, maxY: Int){
        y = y.dec().coerceIn(minY..maxY)
    }

    fun printPlayerCoords(){
        println("X:${x},Y:${y},D:${d}${f}")
    }
    fun getPlayerCoords(): String{
        return "X:${x},Y:${y},D:${d}${f}"
    }

    fun stepCounter (): String {
        val playerY = y
        val playerX = x
        val initialY = 0
        val initialX = 0
        if (playerY != initialY || playerX != initialX) {
            counter = (counter + 1)
        }
        println(counter)
        return "Steps: $counter"
    }

}



    //objective: Create a 3D Array to represent each level.
    //Each Array element needs 4 images (North, South, East, and West).
    //When we move, we increase the desired element by one and check to see if the image exists.
    //So level one is an array at [X][Y][0]
    //level two is an array at [X][Y][1]
    //If Direction is North and we press up, we travel to [X][Y+1][0]DN
    //if [X][Y+1][0].northImage does not exist, we write "Ouch!" to label because it's a wall.

    //Creating an Object of items to go into the 3D Array.

class mapCell(X: Int?, Y: Int?, F: Int) {

    val northImagePath = "src/main/resources/maps/F1/X${X}Y${Y}DNF${F}.png"
    val southImagePath = "src/main/resources/maps/F1/X${X}Y${Y}DSF${F}.png"
    val eastImagePath = "src/main/resources/maps/F1/X${X}Y${Y}DEF${F}.png"
    val westImagePath = "src/main/resources/maps/F1/X${X}Y${Y}DWF${F}.png"

    val northImage = ImageIcon(northImagePath)
    val southImage = ImageIcon(southImagePath)
    val eastImage = ImageIcon(eastImagePath)
    val westImage = ImageIcon(westImagePath)

    fun FileCheck(): Boolean {
        if (File(northImagePath).exists() &&
            File(southImagePath).exists() &&
            File(eastImagePath).exists() &&
            File(westImagePath).exists()
        ) {
            //println("image loaded")
            return true
        } else {
            //println("The location does not exist on this map!")
            return false
        }
    }
}

fun buildLevel(levelX: Int, levelY: Int, f: Int) :kotlin.Array<kotlin.Array<mapCell?>>{
    val xyArray = Array(levelX) { arrayOfNulls<mapCell?>(levelY) }
    println("Loading level")
    for (x in 0 until levelX) {
        for (y in 0 until levelY) {
            val cell = mapCell(x, y, f)
            if (cell.FileCheck()) {
                println("Loading level assets.")
                xyArray[x][y] = cell
            }
        }
    }
    println("Loading complete")
    println("May fortune be on your side.")
    return xyArray
}




    //possible X = [0,1,2,3,4,5,6]
    //possible Y = [0,1,2,3,4,5,6]
    //check to see if all combinations of these X and Y exist in the following path:
    //"src/main/resources/maps/F1X${X}Y${Y}DNF${F}.png"
    //check to see if file exists. Example check for file "src/main/resources/mapsF1X0Y0DNF.png"
    //if file exists, add it "src/main/resources/mapsF1X0Y0DNF.png" to its proper index. Aka add src/main/resources/mapsF1X0Y0DNF to array(0,0,0)


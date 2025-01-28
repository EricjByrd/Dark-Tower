package io.github.ericjbyrd.partnershipproject

import com.jidesoft.swing.Sticky
import io.github.ericjbyrd.partnershipproject.playerPosition.d
import io.github.ericjbyrd.partnershipproject.playerPosition.f
import io.github.ericjbyrd.partnershipproject.playerPosition.x
import io.github.ericjbyrd.partnershipproject.playerPosition.y
import java.io.File
import jdk.internal.org.jline.utils.Colors.h
import jdk.internal.org.jline.utils.Colors.s
import net.miginfocom.layout.LinkHandler.X
import net.miginfocom.layout.LinkHandler.Y
import java.awt.Image
import java.nio.file.Path
import java.sql.Array
import javax.swing.ImageIcon
import javax.swing.text.html.HTML.Attribute.N

fun CheckBounds(path: String): Boolean {
    val item = File(path)
    val result = if (!item.exists()){
        return true
    }
    else{
        return false
    }
    return result
}

object playerPosition {
    //this can serve as coordinates AND locations for the map.
    //Idea credited to the brilliant RyiSnow!
    var counter = 0
    var x: Int = 0
    fun coordCheck(coord: Int){
        if (coord == 0){
            println("Cannot go any further")
        }
    }
    var y: Int = 0
    var d: Direction = Direction.N
    enum class Direction{
        N,S,E,W
    }
    var f: Int = 1
    //I decided to include dynamic coordinates for easier programming.
    val coordinates= "X:${x},Y:${y},D:${d}F${f}"
    fun printPlayerCoords(){
        println("X:${x},Y:${y},D:${d}${f}")
    }
    fun getPlayerCoords(): String{
        return "X:${x},Y:${y},D:${d}${f}"
    }

    fun stepCounter (): String {
        var playerY = y
        var playerX = x
        var initialY = 0
        var initialX = 0
        if (playerY != initialY || playerX != initialX) {
            counter++
        }
        println(counter)
        return "Steps: ${counter}"
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

fun buildLevel() :kotlin.Array<kotlin.Array<mapCell?>>{
    val pX = 20
    val pY = 20
    val xyArray = Array(pX) { arrayOfNulls<mapCell?>(pY) }
    println("Loading level")

    for (x in 0 until pX) {
        for (y in 0 until pY) {
            val cell = mapCell(x, y, 1)
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


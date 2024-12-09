package io.github.ericjbyrd.partnershipproject

import com.jidesoft.swing.Sticky
import io.github.ericjbyrd.partnershipproject.playerPosition.d
import io.github.ericjbyrd.partnershipproject.playerPosition.f
import io.github.ericjbyrd.partnershipproject.playerPosition.x
import io.github.ericjbyrd.partnershipproject.playerPosition.y
import java.io.File
import jdk.internal.org.jline.utils.Colors.h
import jdk.internal.org.jline.utils.Colors.s
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

}

//function to potentially add images to coordinates.
    fun addViews(list: ArrayList<String>, x: Int, y: Int, f: Int) {
        val north: String = "src/main/resources/maps/F1/X${x}Y${y}DNF${f}.png"
        val south: String = "src/main/resources/maps/F1/X${x}Y${y}DSF${f}.png"
        val east: String = "src/main/resources/maps/F1/X${x}Y${y}DEF${f}.png"
        val west: String = "src/main/resources/maps/F1/X${x}Y${y}DWF${f}.png"
        list.add(north)
        list.add(south)
        list.add(east)
        list.add(west)
    }


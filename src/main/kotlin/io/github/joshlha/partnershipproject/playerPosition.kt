package io.github.joshlha.partnershipproject

import javax.swing.text.html.HTML.Attribute.N

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
    val coordinates= "X:${x},Y:${y},D:${d}"
    val usableCoordinates ="X${x}Y${y}D${d}F${f}"
    fun printPlayerCoords(){
        println("X:${x},Y:${y},D:${d}")
    }
}
